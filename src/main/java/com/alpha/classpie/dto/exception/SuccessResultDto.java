package com.alpha.classpie.dto.exception;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author 杨能
 * @create 2020/10/15
 */


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuccessResultDto extends ResultDto {
    private Object data;
}
