package com.alpha.classpie.dto.warpper;

import com.alpha.classpie.pojo.file.AttachmentResources;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 杨能
 * @create 2020/11/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AttachmentResourcesWrapper extends AttachmentResources {
    long size;
}
