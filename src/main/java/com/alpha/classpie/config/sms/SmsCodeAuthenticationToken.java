package com.alpha.classpie.config.sms;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * @author 杨能
 * @create 2020/11/7
 */
public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    // ~ Instance fields
    // ================================================================================================

    private final String telephone;
    private Integer captcha;


    public SmsCodeAuthenticationToken(String telephone, Integer captcha) {
        super(null);
        this.telephone = telephone;
        this.captcha = captcha;
        setAuthenticated(false);
    }

    public SmsCodeAuthenticationToken(String telephone, Integer captcha,
                                      Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.telephone = telephone;
        this.captcha = captcha;
        super.setAuthenticated(true); // must use super, as we override
    }

    // ~ Methods
    // ========================================================================================================

    public Integer getCredentials() {
        return this.captcha;
    }

    public String getPrincipal() {
        return this.telephone;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        captcha = null;
    }
}
