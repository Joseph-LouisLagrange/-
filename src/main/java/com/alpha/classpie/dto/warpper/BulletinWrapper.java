package com.alpha.classpie.dto.warpper;

import com.alpha.classpie.pojo.bulletin.Bulletin;
import com.alpha.classpie.pojo.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class BulletinWrapper extends Bulletin {
    long readCount=-1;
    long commentCount=-1;
    User publisher;
}
