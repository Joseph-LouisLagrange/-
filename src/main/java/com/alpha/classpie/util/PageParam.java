package com.alpha.classpie.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParam {
    int pageNumber=1;
    int pageSize=1;
}
