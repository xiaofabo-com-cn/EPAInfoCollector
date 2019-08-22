package cn.com.xiaofabo.hca.epainfocollector.admin.interfaces.web;

import cn.com.xiaofabo.hca.epainfocollector.entity.TbCrawlFile;
import cn.com.xiaofabo.hca.epainfocollector.service.PersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Jonsy
 *
 */
@Controller
public class IndexController {
    @Autowired
    PersistenceService persistenceService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/download/**/{fileName}.{fileSuffix}")
    public String download(@PathVariable("fileName")String fileName,
                           @PathVariable("fileSuffix")String fileSuffix, HttpServletRequest request) {
        TbCrawlFile tbCrawlFile = persistenceService.queryFileByUrl(request.getServletPath().replace("/download",""));
        if (tbCrawlFile != null){
            return "redirect:/business/"+tbCrawlFile.getId()+"/download";
        }
        return "/error/404";
    }
}
