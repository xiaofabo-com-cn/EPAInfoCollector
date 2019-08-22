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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;


@Controller
@RequestMapping("/iframe")
public class IframeController {

    private static Logger logger = LoggerFactory.getLogger(IframeController.class);

    @RequestMapping(value ="/index", method = RequestMethod.GET)
    public String list(Model model, @RequestParam(value = "para") String para){
        String ip = "http://";
        try {
            InetAddress addr = InetAddress.getLocalHost();
            ip = ip + addr.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            ip = ip + "localhost";
        }
        ip = ip + ":8081/";
        model.addAttribute("url",ip + para);
        return "iframe/iframe";
    }
}
