package com.alpha.classpie.service.inf;

import com.alpha.classpie.dto.EducationTree;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/17
 * 信息服务
 */
@Service
public interface InfoService {
    public List<EducationTree> getEducationTrees();
}
