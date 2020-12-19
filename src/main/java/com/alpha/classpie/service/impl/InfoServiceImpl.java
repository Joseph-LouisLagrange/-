package com.alpha.classpie.service.impl;

import com.alpha.classpie.dao.EducationLevelMapper;
import com.alpha.classpie.dto.EducationTree;
import com.alpha.classpie.service.inf.InfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class InfoServiceImpl implements InfoService {

    @Autowired
    EducationLevelMapper educationLevelMapper;

    @Override
    public List<EducationTree> getEducationTrees() {
        return educationLevelMapper.findEducationTree();
    }
}
