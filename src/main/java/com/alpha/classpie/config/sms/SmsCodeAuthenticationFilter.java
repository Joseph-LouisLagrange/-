package com.alpha.classpie.config.sms;

import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    // =====================================================================================

    public static final String SPRING_SECURITY_PARAMETER_PHONE_NUMBER = "telephone";
    public static final String SPRING_SECURITY_PARAMETER_CODE_KEY = "captcha";

    private String phoneParameter = SPRING_SECURITY_PARAMETER_PHONE_NUMBER;
    private String codeParameter = SPRING_SECURITY_PARAMETER_CODE_KEY;
    private boolean postOnly = true;


    // ~ Constructors
    // ===================================================================================================

    public SmsCodeAuthenticationFilter () {
        super(new AntPathRequestMatcher("/user/smsLogin", "POST"));
    }

    // ~ Methods
    // ========================================================================================================

    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        String phoneNumber = obtainPhoneNumber(request);
        Integer code = obtainCode(request);

        if (phoneNumber == null) {
            phoneNumber = "";
        }

        if (code == null) {
            code = -1;
        }

        phoneNumber = phoneNumber.trim();

        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(phoneNumber, code);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }


    @Nullable
    protected Integer obtainCode(HttpServletRequest request) {
        return Integer.valueOf(request.getParameter(codeParameter));
    }


    @Nullable
    protected String obtainPhoneNumber(HttpServletRequest request) {
        return request.getParameter(phoneParameter);
    }


    protected void setDetails(HttpServletRequest request,
                              SmsCodeAuthenticationToken  authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setPhoneParameter(String phoneParameter) {
        Assert.hasText(phoneParameter, "Phone parameter must not be empty or null");
        this.phoneParameter = phoneParameter;
    }


    public void setCodeParameter(String codeParameter) {
        Assert.hasText(codeParameter, "Code parameter must not be empty or null");
        this.codeParameter = codeParameter;
    }


    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getPhoneParameter() {
        return phoneParameter;
    }

    public final String getCodeParameter() {
        return codeParameter;
    }
}
