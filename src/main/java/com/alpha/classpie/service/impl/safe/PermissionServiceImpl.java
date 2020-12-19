package com.alpha.classpie.service.impl.safe;

import com.alpha.classpie.pojo.user.Permission;
import com.alpha.classpie.pojo.user.Role;
import com.alpha.classpie.service.inf.safe.PermissionService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Service("defaultPermissionService")
public class PermissionServiceImpl implements PermissionService {
    @Override
    public Set<Permission> getPermissionsByRole(Role role) {
        return new HashSet<Permission>(Arrays.asList(new Permission(1,"权限名1","权限描述1")
        ,new Permission(2,"权限名2","权限描述2")));
    }

}
