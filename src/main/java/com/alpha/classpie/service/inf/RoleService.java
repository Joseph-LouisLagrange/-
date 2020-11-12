package com.alpha.classpie.service.inf;

import com.alpha.classpie.pojo.user.Role;
import org.springframework.stereotype.Service;

/**
 * @author 杨能
 * @create 2020/11/1
 */
@Service
public interface RoleService {
    public Role getRoleById(int roleId);
    public Role getRoleByName(String name);
}
