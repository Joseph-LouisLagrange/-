package com.alpha.classpie.service.impl.safe;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.util.RandomUtil;
import com.aliyuncs.exceptions.ClientException;
import com.alpha.classpie.dto.exception.ExceptionDto;
import com.alpha.classpie.pojo.other.SMSResponse;
import com.alpha.classpie.rdao.*;
import com.alpha.classpie.service.inf.safe.CaptchaService;
import com.alpha.classpie.service.inf.notice.MailService;
import com.alpha.classpie.util.AlibabaSMSUtil;
import com.alpha.classpie.util.MathGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.awt.*;
import java.io.IOException;

/**
 * @author 杨能
 * @create 2020/11/2
 */
@Service("defaultCaptchaService")
public class CaptchaServiceImpl implements CaptchaService {

    public static final String WEB_REGISTER_GRAPH="WEB_REGISTER_GRAPH";

    public static final String WEB_LOGIN_GRAPH="WEB_LOGIN_GRAPH";

    public static final String WEB_USERNAME_BIND_GRAPH ="WEB_USERNAME_BIND_GRAPH";

    public static final String SMS_REGISTER_CODE="SMS_REGISTER_CODE";

    public static final String EMAIL_REGISTER_CODE="EMAIL_REGISTER_CODE";

    public static final String SMS_LOGIN_CODE="SMS_LOGIN_CODE";

    public static final String SMS_BIND_CODE="SMS_BIND_CODE";

    public static final String EMAIL_BIND_CODE="EMAIL_BIND_CODE";


    @Autowired
    MathGenerator mathGenerator;

    @Resource(name = "webCaptchaDao")
    CaptchaDao<String> webCaptchaDao;

    @Resource(name = "smsCaptchaDao")
    CaptchaDao<Integer> smsCaptchaDao;

    @Resource(name = "emailCaptchaDao")
    CaptchaDao<Integer> emailCaptchaDao;

    @Autowired
    AlibabaSMSUtil alibabaSMSUtil;

    @Resource(name = "defaultMailService")
    MailService mailService;

    @Override
    public long getLoginSMSCaptchaExpireSeconds(String phone) {
        return smsCaptchaDao.getExpireSeconds(phone,SMS_LOGIN_CODE);
    }

    @Override
    public long getRegisterSMSCaptchaExpireSeconds(String phone) {
        return smsCaptchaDao.getExpireSeconds(phone,SMS_REGISTER_CODE);
    }

    @Override
    public long getRegisterEmailCaptchaExpireSeconds(String emailAddress) {
        return emailCaptchaDao.getExpireSeconds(emailAddress,EMAIL_REGISTER_CODE);
    }

    @Override
    public long getPhoneBindSMSCaptchaExpireSeconds(String phone) {
        return 0;
    }

    @Override
    public long getEmailBindCaptchaExpireSeconds(String emailAddress) {
        return 0;
    }

    @Override
    @Transactional
    public SMSResponse sendRegisterSMSCaptcha(String phone) throws ClientException {
        return internalSendSMSCaptcha(phone,SMS_REGISTER_CODE);
    }

    @Override
    public boolean sendRegisterEmailCaptcha(@Email String emailAddress) {
        if(emailCaptchaDao.hasCaptcha(emailAddress,EMAIL_REGISTER_CODE)){
            return false;
        }
        int code = RandomUtil.randomInt(1000, 9999);
        mailService.sendCaptcha(emailAddress,code);
        //缓存验证码
        emailCaptchaDao.setCaptcha(emailAddress,EMAIL_REGISTER_CODE,code);
        return true;
    }


    private SMSResponse internalSendSMSCaptcha(String phone,String captchaId) throws ClientException {
        //判断验证码是否过期
        if(smsCaptchaDao.hasCaptcha(phone,captchaId)){
            //表示缓存里面的验证码还在，不能再发了
            return SMSResponse.REFUSE;
        }else{
            //随机一个code
            int code = RandomUtil.randomInt(1000, 10000);
            //异步发送验证码
            internalAsyncSendSMS(phone,code);
            //存入缓存中
            smsCaptchaDao.setCaptcha(phone,captchaId,code);
        }
        return SMSResponse.SUCCESS;
    }


    @Override
    public SMSResponse sendLoginSMSCaptcha(String phone) throws ClientException {
        return internalSendSMSCaptcha(phone,SMS_LOGIN_CODE);
    }

    @Override
    public SMSResponse sendPhoneBindSMSCaptcha(String phone) throws ClientException {
        return null;
    }

    @Override
    public boolean sendEmailBindCaptcha(String email) {
        return false;
    }


    protected SMSResponse internalSyncSendSMS(String phone,int code) throws ClientException {
        return alibabaSMSUtil.sendCaptcha(code,phone);
    }

    @Async
    protected void internalAsyncSendSMS(String phone,int code) throws ClientException {
        alibabaSMSUtil.sendCaptcha(code,phone);
    }

    @Override
    @Transactional
    public void getRegisterChineseWebMathCaptcha(int width, int height, String virtualId, HttpServletResponse httpServletResponse) throws IOException {
        String code=sendChineseWebMathCaptcha(width,height,httpServletResponse);
        //保存验证码到Redis缓存区
        webCaptchaDao.setCaptcha(virtualId,WEB_REGISTER_GRAPH,code);
    }

    @Override
    public void getBindChineseWebMathCaptcha(@Min(1) int width, @Min(1) int height, int userId, HttpServletResponse httpServletResponse) throws IOException {
        String code=sendChineseWebMathCaptcha(width,height,httpServletResponse);
        //保存验证码到Redis缓存区
        webCaptchaDao.setCaptcha(String.valueOf(userId), WEB_USERNAME_BIND_GRAPH,code);
    }

    protected String sendChineseWebMathCaptcha(@Min(1) int width, @Min(1) int height,HttpServletResponse httpServletResponse) throws IOException {
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(width, height, 4, 4);
        circleCaptcha.setBackground(Color.WHITE);
        circleCaptcha.setFont(new Font(null,Font.PLAIN,width/6));
        circleCaptcha.setGenerator(mathGenerator);
        circleCaptcha.createCode();
        circleCaptcha.write(httpServletResponse.getOutputStream());
        return circleCaptcha.getCode();
    }

    @Override
    public void getLoginChineseWebMathCaptcha(int width, int height, String virtualId, HttpServletResponse httpServletResponse) throws IOException {
        String code=sendChineseWebMathCaptcha(width,height,httpServletResponse);
        //保存验证码到Redis缓存区
        webCaptchaDao.setCaptcha(virtualId,WEB_LOGIN_GRAPH,code);
    }

    private boolean internalCheckSMSCaptcha(String phone,int code,String captchaId) throws ExceptionDto {
        Integer oldCode = smsCaptchaDao.getCode(phone, captchaId);
        if(oldCode==null){
            //表示已经过期,或者压根就没有发送出去
            throw new ExceptionDto("验证码失效","短信验证码失效","重新发送");
        }
        return oldCode.equals(code);
    }

    @Override
    public boolean checkRegisterSMSCaptcha(String phone, int code) throws ExceptionDto {
        return internalCheckSMSCaptcha(phone,code,SMS_REGISTER_CODE);
    }

    @Override
    public boolean checkRegisterEmailCaptcha(@Email String emailAddress, int code) {
        Integer c = emailCaptchaDao.getCode(emailAddress, EMAIL_REGISTER_CODE);
        if(c==null){
            throw new ExceptionDto("验证码失效","未发送或者验证码超时","重新刷新发送");
        }else {
            return c.equals(code);
        }
    }

    @Override
    public boolean checkRegisterChineseWebMathCaptcha(String virtualId, int number) throws ExceptionDto {

        String code = webCaptchaDao.getCode(virtualId, WEB_REGISTER_GRAPH);
        return checkChineseWebMathCaptcha(code,number);
    }

    @Override
    public boolean checkBindChineseWebMathCaptcha(int userId, int number) {
        String code = webCaptchaDao.getCode(String.valueOf(userId), WEB_USERNAME_BIND_GRAPH);
        return checkChineseWebMathCaptcha(code,number);
    }

    @Override
    public boolean checkPhoneBindSMSCaptcha(String phone, int code) {
        return false;
    }

    @Override
    public boolean checkEmailBindCaptcha(String emailAddress, int code) {
        return false;
    }

    private boolean checkChineseWebMathCaptcha(String code,int number){
        if(code==null){
            throw new ExceptionDto("图形验证码失效","页面停留时间过长","重新刷新页面");
        }
        return mathGenerator.verify(code,String.valueOf(number));
    }

    @Override
    public boolean checkLoginChineseWebMathCaptcha(String virtualId, int number) throws ExceptionDto {
        String code = webCaptchaDao.getCode(virtualId, WEB_LOGIN_GRAPH);
        return checkChineseWebMathCaptcha(code,number);
    }

    @Override
    public boolean checkLoginSMSCaptcha(String phone, int code) throws ExceptionDto {
        return internalCheckSMSCaptcha(phone,code,SMS_LOGIN_CODE);
    }

    @Override
    public void deleteRegisterSMSCaptcha(String phone) {
        smsCaptchaDao.removeCaptcha(phone,SMS_REGISTER_CODE);
    }

    @Override
    public void deleteRegisterEmailCaptcha(@Email String email) {
        emailCaptchaDao.removeCaptcha(email,EMAIL_REGISTER_CODE);
    }

    @Override
    public void deleteRegisterChineseWebMathCaptcha(HttpSession session) {
        webCaptchaDao.removeCaptcha(session.getId(),WEB_REGISTER_GRAPH);
    }

    @Override
    public void deleteBindChineseWebMathCaptcha(int userId) {
        webCaptchaDao.removeCaptcha(String.valueOf(userId), WEB_USERNAME_BIND_GRAPH);
    }

    @Override
    public void deleteLoginSMSCaptcha(String phone) {
        smsCaptchaDao.removeCaptcha(phone,SMS_LOGIN_CODE);
    }

    @Override
    public void deletePhoneBindSMSCaptcha(String phone) {

    }

    @Override
    public void deleteEmailBindCaptcha(String address) {

    }
}
