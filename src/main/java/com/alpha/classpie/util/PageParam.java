package com.alpha.classpie.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * @author 杨能
 * @create 2020/11/9
 * 分页参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParam {
    int pageNumber=1;
    int pageSize=1;
}
