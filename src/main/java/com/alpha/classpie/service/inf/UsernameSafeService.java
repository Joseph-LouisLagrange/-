package com.alpha.classpie.service.inf;

import com.alpha.classpie.rdao.VirtualSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 杨能
 * @create 2020/11/12
 * 这是一个保证username一致性的工具
 * 在实际业务中 可能会有各种信息劫持，比如：发送注册验证码的电话号，和最后提交的json中的电话号不一致等等。。。
 */
public interface UsernameSafeService {

}
