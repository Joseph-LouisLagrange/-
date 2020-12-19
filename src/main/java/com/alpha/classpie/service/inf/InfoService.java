package com.alpha.classpie.service.inf;

import com.alpha.classpie.dto.EducationTree;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface InfoService {
    public List<EducationTree> getEducationTrees();
}
