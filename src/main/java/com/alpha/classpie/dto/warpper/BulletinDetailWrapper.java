package com.alpha.classpie.dto.warpper;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BulletinDetailWrapper extends BulletinWrapper {
    List<CommentWrapper> commentWrappers=new ArrayList<>();
}
