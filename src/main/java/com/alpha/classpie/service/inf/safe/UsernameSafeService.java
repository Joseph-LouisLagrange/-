package com.alpha.classpie.service.inf.safe;

import com.alpha.classpie.rdao.VirtualSession;
import org.springframework.beans.factory.annotation.Autowired;


public interface UsernameSafeService {
    public void doRegisterUsernameSafe(String virtualId,String username);
    public void checkRegisterUsernameSafe(String virtualId,String username);
    public void doLoginUsernameSafe(String virtualId,String username);
    public void checkLoginUsernameSafe(String virtualId,String username);
}
