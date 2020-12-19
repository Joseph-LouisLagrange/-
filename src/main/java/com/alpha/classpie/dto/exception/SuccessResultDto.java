package com.alpha.classpie.dto.exception;

import lombok.*;
import org.springframework.stereotype.Component;




@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuccessResultDto extends ResultDto {
    private Object data;
}
