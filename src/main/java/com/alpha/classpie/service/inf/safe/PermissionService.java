package com.alpha.classpie.service.inf.safe;

import com.alpha.classpie.pojo.user.Permission;
import com.alpha.classpie.pojo.user.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author 杨能
 * @create 2020/11/1
 */
@Service
public interface PermissionService {
    public Set<Permission> getPermissionsByRole(Role role);
}
