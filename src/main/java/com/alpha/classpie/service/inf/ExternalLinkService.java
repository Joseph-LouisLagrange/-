package com.alpha.classpie.service.inf;

import com.alpha.classpie.pojo.ExternalLink;

import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/26
 */
public interface ExternalLinkService {
    public ExternalLink addExternalLink(ExternalLink externalLink);

    public boolean deleteExternalLink(int id);

    public List<ExternalLink> getExternalLinks(int courseId);

    public ExternalLink updateExternalLink(ExternalLink externalLink);
}
