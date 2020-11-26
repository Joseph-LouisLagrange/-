package com.alpha.classpie.service.inf;

import com.alpha.classpie.util.PageParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/9
 */
@Service
public interface BaseService<T,E> {
    //万能的分页查询
    default PageInfo<T> page(PageParam pageParam,E example){
        return PageHelper.startPage(pageParam.getPageNumber(),pageParam.getPageSize())
                .doSelectPageInfo(()->{
                    list(example);});
    }
    List<T> list(E example);
}
