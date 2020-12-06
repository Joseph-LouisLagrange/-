package com.alpha.classpie.service.impl;

import com.alpha.classpie.dao.ExternalLinkMapper;
import com.alpha.classpie.example.ExternalLinkExample;
import com.alpha.classpie.pojo.ExternalLink;
import com.alpha.classpie.service.inf.ExternalLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/26
 */
@Service("defaultExternalLinkService")
public class ExternalLinkServiceImpl implements ExternalLinkService {

    @Autowired
    ExternalLinkMapper externalLinkMapper;

    public ExternalLink wrap(ExternalLink externalLink){
        externalLink.setId(null);
        externalLink.setDatetime(new Date());
        return externalLink;
    }

    @Override
    public ExternalLink addExternalLink(ExternalLink externalLink) {
        externalLinkMapper.insert(wrap(externalLink));
        return externalLink;
    }

    @Override
    public boolean deleteExternalLink(int id) {
        return externalLinkMapper.deleteByPrimaryKey(id)>0;
    }

    @Override
    public List<ExternalLink> getExternalLinks(int courseId) {
        ExternalLinkExample externalLinkExample = new ExternalLinkExample();
        externalLinkExample.createCriteria().andCourseIdEqualTo(courseId);
        return externalLinkMapper.selectByExample(externalLinkExample);
    }

    @Override
    public ExternalLink updateExternalLink(ExternalLink externalLink) {
        externalLink.setDatetime(new Date());
        externalLinkMapper.updateByPrimaryKeySelective(externalLink);
        return externalLink;
    }
}
