package com.alpha.classpie.service.inf.safe;

import com.alpha.classpie.pojo.user.Permission;
import com.alpha.classpie.pojo.user.Role;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public interface PermissionService {
    public Set<Permission> getPermissionsByRole(Role role);
}
