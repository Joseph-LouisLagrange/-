package com.alpha.classpie.dto.warpper;

import com.alpha.classpie.pojo.file.UserFile;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class UserFileWrapper extends UserFile {
    long size;
}
