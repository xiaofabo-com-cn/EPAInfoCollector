package cn.com.xiaofabo.hca.epainfocollector.admin.interfaces.web;

import cn.com.xiaofabo.hca.epainfocollector.common.Constant;
import cn.com.xiaofabo.hca.epainfocollector.entity.*;
import cn.com.xiaofabo.hca.epainfocollector.entity.req.CrawlUrlReq;
import cn.com.xiaofabo.hca.epainfocollector.mail.MailService;
import cn.com.xiaofabo.hca.epainfocollector.service.CrawService;
import cn.com.xiaofabo.hca.epainfocollector.service.PersistenceService;
import cn.com.xiaofabo.hca.epainfocollector.task.CrawEngine;
import cn.com.xiaofabo.hca.epainfocollector.task.ExpireData;
import cn.com.xiaofabo.hca.epainfocollector.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;


@Controller
@RequestMapping("/business")
public class BusinessController {

    private static Logger logger = LoggerFactory.getLogger(BusinessController.class);

    @Autowired
    PersistenceService persistenceService;
    @Autowired
    CrawService crawService;

    @RequestMapping(value ="/list", method = RequestMethod.GET)
    public String list(Model model){
        CrawlUrlReq crawlUrlReq = new CrawlUrlReq();
        model.addAttribute("list",persistenceService.urlList(crawlUrlReq));
        return "business/url-list";
    }

    @RequestMapping(value ="/{id}/detail", method = RequestMethod.GET)
    public String detail(Model model, @PathVariable("id") Integer id){
        TbCrawlUrl tbCrawlUrl = persistenceService.getUrlById(id);
        if (tbCrawlUrl != null){
            TbCrawlContent tbCrawlContent = persistenceService.urlDetail(tbCrawlUrl.getUrl());
            List<TbCrawlFile> fileList = persistenceService.urlFile(tbCrawlUrl.getUrl());
            if (tbCrawlContent != null){
                try {
                    String detail = new String(tbCrawlContent.getBodyContent(),"UTF-8");
                    model.addAttribute("detail", detail);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                model.addAttribute("title", tbCrawlContent.getTitle());
            } else {
                TbCrawlFile file = persistenceService.queryFileByUrl(tbCrawlUrl.getUrl());
                if (file != null){
                    return "redirect:/business/"+file.getId()+"/download";
                }
            }
            model.addAttribute("fileList", fileList);
        }
        return "business/url-detail";
    }

    @RequestMapping(value = "/setting/notify",method = RequestMethod.GET)
    public String getNotify(Model model){
        String api="/business/setting/notify/edit";
        List<TbCrawlDict> dictList1 = persistenceService.getDictByKey(MailService.DICT_MAIL_KEY);
        if (!CollectionUtils.isEmpty(dictList1)){
            model.addAttribute("email", dictList1.parallelStream().findFirst().get());
        }
        List<TbCrawlDict> dictList2 = persistenceService.getDictByKey(MailService.DICT_MAIL_CRON_KEY);
        if (!CollectionUtils.isEmpty(dictList2)){
            model.addAttribute("cron", dictList2.parallelStream().findFirst().get());
        }

        List<TbCrawlDict> dictList3 = persistenceService.getDictByKey(MailService.DICT_MAIL_NO_SEND);
        if (!CollectionUtils.isEmpty(dictList3)){
            model.addAttribute("noEmail", dictList3.parallelStream().findFirst().get());
        }
        model.addAttribute("api",api);
        return  "business/setting/setting-email";
    }

    @RequestMapping(value = "/setting/notify/edit", method = RequestMethod.POST)
    public String editNotify(Model model, @RequestParam(value = "emailId", required = false)Integer emailId,
                             @RequestParam(value = "emails")String emails,
                             @RequestParam(value = "cronId", required = false)Integer cronId,
                             @RequestParam(value = "cron")String cron,
                             @RequestParam(value = "noEmailId", required = false)Integer noEmailId,
                             @RequestParam(value = "noEmail")String noEmail){
        TbCrawlDict emailDict = new TbCrawlDict();
        emailDict.setId(emailId);
        emailDict.setdKey(MailService.DICT_MAIL_KEY);
        emailDict.setdValue(emails);
        persistenceService.editDict(emailDict);

        TbCrawlDict cronDict = new TbCrawlDict();
        cronDict.setId(cronId);
        cronDict.setdKey(MailService.DICT_MAIL_CRON_KEY);
        cronDict.setdValue(cron);
        persistenceService.editDict(cronDict);

        TbCrawlDict noEmailDict = new TbCrawlDict();
        noEmailDict.setId(noEmailId);
        noEmailDict.setdKey(MailService.DICT_MAIL_NO_SEND);
        noEmailDict.setdValue(noEmail);
        persistenceService.editDict(noEmailDict);
        return "redirect:/business/setting/notify";
    }

    @RequestMapping(value = "/setting/rule", method = RequestMethod.GET)
    public String getRule(Model model){
        model.addAttribute("list",persistenceService.getAllCrawRule(false));
        return  "business/setting/setting-rule";
    }

    @RequestMapping(value = "/setting/rule/{id}/status", method = RequestMethod.PUT)
    @ResponseBody
    public void ruleStatusChange(@PathVariable("id") Integer id,@RequestParam("isDelete") Integer isDelete){
        TbCrawlRule tbCrawlRule = new TbCrawlRule();
        tbCrawlRule.setId(id);
        tbCrawlRule.setIsDelete(isDelete);
        persistenceService.changeRuleStatus(tbCrawlRule);
    }

    @RequestMapping(value = "/{id}/download", method = RequestMethod.GET)
    @ResponseBody
    public Object download(@PathVariable("id") Integer id){
        TbCrawlFile file = persistenceService.getFileById(id);
        if (null == file){
            return null;
        }
        ResponseEntity<InputStreamResource> response = null;
        try {
            response = CommonUtil.download(Constant.FILE_PATH, file.getFileMd5(), file.getFileName());
        } catch (Exception e) {
            logger.error("下载失败 id={}",id);
        }
        return response;

    }

    @RequestMapping(value = "/setting/task",method = RequestMethod.GET)
    public String getTask(Model model){
        String api="/business/setting/task/edit";
        List<TbCrawlDict> dictList1 = persistenceService.getDictByKey(CrawEngine.DICT_KEY);
        if (!CollectionUtils.isEmpty(dictList1)){
            model.addAttribute("crawTask", dictList1.parallelStream().findFirst().get());
        }
        model.addAttribute("api",api);
        return  "business/setting/setting-task";
    }

    @RequestMapping(value = "/setting/task/edit", method = RequestMethod.POST)
    public String editTask(Model model, @RequestParam(value = "taskId", required = false)Integer taskId,
                             @RequestParam(value = "cron")String cron){
        TbCrawlDict taskDict = new TbCrawlDict();
        taskDict.setId(taskId);
        taskDict.setdKey(CrawEngine.DICT_KEY);
        taskDict.setdValue(cron);
        persistenceService.editDict(taskDict);

        return "redirect:/business/setting/task";
    }

    @RequestMapping(value = "/setting/expire",method = RequestMethod.GET)
    public String getExpire(Model model){
        String api="/business/setting/expire/edit";
        List<TbCrawlDict> dictList1 = persistenceService.getDictByKey(ExpireData.DICT_EXPIRE_DAY_KEY);
        if (!CollectionUtils.isEmpty(dictList1)){
            model.addAttribute("expireDay", dictList1.parallelStream().findFirst().get());
        }

        List<TbCrawlDict> dictList2 = persistenceService.getDictByKey(ExpireData.DICT_EXPIRE_CRON_KEY);
        if (!CollectionUtils.isEmpty(dictList2)){
            model.addAttribute("expireCron", dictList2.parallelStream().findFirst().get());
        }
        model.addAttribute("api",api);
        return  "business/setting/setting-expire";
    }

    @RequestMapping(value = "/setting/expire/edit", method = RequestMethod.POST)
    public String editExpire(Model model, @RequestParam(value = "dayId", required = false)Integer dayId,
                           @RequestParam(value = "day")String day,
                           @RequestParam(value = "cronId", required = false)Integer cronId,
                           @RequestParam(value = "cron")String cron){
        TbCrawlDict dayDict = new TbCrawlDict();
        dayDict.setId(dayId);
        dayDict.setdKey(ExpireData.DICT_EXPIRE_DAY_KEY);
        dayDict.setdValue(day);
        persistenceService.editDict(dayDict);

        TbCrawlDict cronDict = new TbCrawlDict();
        cronDict.setId(cronId);
        cronDict.setdKey(ExpireData.DICT_EXPIRE_CRON_KEY);
        cronDict.setdValue(cron);
        persistenceService.editDict(cronDict);

        return "redirect:/business/setting/expire";
    }

    @RequestMapping(value ="/collect", method = RequestMethod.GET)
    public String collect(Model model){
        String api="/business/collect/sendMail";
        model.addAttribute("list",persistenceService.getAllCrawRule(false));
        model.addAttribute("api",api);
        return "business/collect";
    }

    @RequestMapping(value ="/collect/sendMail", method = RequestMethod.POST)
    public String collect(Model model,@RequestParam(value = "channel")String channel,
                          @RequestParam(value = "emails")String emails,
                          @RequestParam(value = "keyword")String keyword){
        crawService.runManual(channel, keyword, emails);
        return "redirect:/business/collect";
    }

}
