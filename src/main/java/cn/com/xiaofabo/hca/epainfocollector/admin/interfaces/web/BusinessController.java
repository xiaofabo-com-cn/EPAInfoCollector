package cn.com.xiaofabo.hca.epainfocollector.admin.interfaces.web;

import cn.com.xiaofabo.hca.epainfocollector.admin.interfaces.facade.assembler.UserAssembler;
import cn.com.xiaofabo.hca.epainfocollector.admin.interfaces.facade.commondobject.UserCommond;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlContent;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlDict;
import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlUrl;
import cn.com.xiaofabo.hca.epainfocollector.entity.req.CrawlUrlReq;
import cn.com.xiaofabo.hca.epainfocollector.mail.MailService;
import cn.com.xiaofabo.hca.epainfocollector.service.PersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.List;


@Controller
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    PersistenceService persistenceService;

    @RequestMapping(method = RequestMethod.GET)
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
            if (tbCrawlContent != null){
                try {
                    String detail = new String(tbCrawlContent.getBodyContent(),"UTF-8");
                    model.addAttribute("detail", detail);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        }
        return "business/url-detail";
    }

    @RequestMapping(value = "/setting/notify",method = RequestMethod.GET)
    public String getNotify(Model model){
        String api="/setting/notify/edit";
        List<TbCrawlDict> dictList = persistenceService.getDictByKey(MailService.DICT_KEY);
        if (!CollectionUtils.isEmpty(dictList)){
            model.addAttribute("email", dictList.parallelStream().findFirst().get());
        }
        model.addAttribute("api",api);
        return  "business/setting/setting-email";
    }

    @RequestMapping(value = "/setting/notify/edit", method = RequestMethod.POST)
    public String editNotify(Model model, TbCrawlDict tbCrawlDict){
        persistenceService.editDict(tbCrawlDict);
        return "redirect:/user";
    }
}
