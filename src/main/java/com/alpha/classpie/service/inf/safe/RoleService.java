package com.alpha.classpie.service.inf.safe;

import com.alpha.classpie.pojo.user.Role;
import org.springframework.stereotype.Service;


@Service
public interface RoleService {
    public Role getRoleById(int roleId);
    public Role getRoleByName(String name);
}
