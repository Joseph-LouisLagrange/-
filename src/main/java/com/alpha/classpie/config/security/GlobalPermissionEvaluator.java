package com.alpha.classpie.config.security;


import com.alpha.classpie.service.inf.safe.PermissionService;
import com.alpha.classpie.service.inf.safe.RoleService;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;

@Component("globalPermissionEvaluator")
public class GlobalPermissionEvaluator implements PermissionEvaluator {

    @Resource(name="defaultRoleService")
    RoleService roleService;


    @Resource(name = "defaultPermissionService")
    PermissionService permissionService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        boolean hasPermission=false;
        if(authentication.isAuthenticated() && permission instanceof Integer){
            Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
           hasPermission=authorities
                    .stream()
                    .map(o -> ((GrantedAuthority)o).getAuthority())
                    .map(roleService::getRoleByName)
                    .map(permissionService::getPermissionsByRole)
                    .flatMap(Collection::stream)
                    .anyMatch(p-> p.getCode() == (int)permission);
        }
        return hasPermission;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
