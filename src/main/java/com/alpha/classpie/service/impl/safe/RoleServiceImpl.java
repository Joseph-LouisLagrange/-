package com.alpha.classpie.service.impl.safe;

import com.alpha.classpie.pojo.user.Role;
import com.alpha.classpie.service.inf.safe.RoleService;
import org.springframework.stereotype.Service;

/**
 * @author 杨能
 * @create 2020/11/1
 */
@Service("defaultRoleService")
public class RoleServiceImpl implements RoleService {
    @Override
    public Role getRoleById(int roleId) {
        return null;
    }

    @Override
    public Role getRoleByName(String name) {
        return null;
    }
}
