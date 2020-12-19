package com.alpha.classpie.controller;

import com.alpha.classpie.pojo.ExternalLink;
import com.alpha.classpie.service.inf.ExternalLinkService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/externalLink")
public class ExternalLinkController {

    @Resource(name = "defaultExternalLinkService")
    ExternalLinkService externalLinkService;

    @RequestMapping("/addExternalLink")
    public ExternalLink addExternalLink(@RequestBody ExternalLink externalLink){
        return externalLinkService.addExternalLink(externalLink);
    }

    @RequestMapping("/deleteExternalLink")
    public boolean deleteExternalLink(@RequestParam("id")int id){
        return externalLinkService.deleteExternalLink(id);
    }

    @RequestMapping("/updateExternalLink")
    public ExternalLink updateExternalLink(@RequestBody ExternalLink externalLink){
        return externalLinkService.updateExternalLink(externalLink);
    }

    @RequestMapping("/getExternalLinks")
    public List<ExternalLink> getExternalLinks(@RequestParam("courseId")int courseId){
       return externalLinkService.getExternalLinks(courseId);
    }
}
