package com.alpha.classpie.dto.warpper;

import com.alpha.classpie.pojo.file.AttachmentResources;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class AttachmentResourcesWrapper extends AttachmentResources {
    long size;
}
