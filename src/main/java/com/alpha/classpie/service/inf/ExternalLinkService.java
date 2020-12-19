package com.alpha.classpie.service.inf;

import com.alpha.classpie.pojo.ExternalLink;

import java.util.List;


public interface ExternalLinkService {
    public ExternalLink addExternalLink(ExternalLink externalLink);

    public boolean deleteExternalLink(int id);

    public List<ExternalLink> getExternalLinks(int courseId);

    public ExternalLink updateExternalLink(ExternalLink externalLink);
}
