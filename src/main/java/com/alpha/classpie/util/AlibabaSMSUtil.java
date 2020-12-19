package com.alpha.classpie.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.alpha.classpie.pojo.other.SMSResponse;
import com.google.gson.Gson;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.concurrent.TimeUnit;


@Data
@Component
public class AlibabaSMSUtil {

    private IAcsClient client = null;
    @Value("${SMS.request.sysMethod}")
    MethodType sysMethod=MethodType.POST;
    @Value("${SMS.request.sysDomain}")
    String sysDomain="dysmsapi.aliyuncs.com";
    @Value("${SMS.request.sysVersion}")
    String sysVersion="2017-05-25";
    @Value("${SMS.request.sysAction}")
    String sysAction="SendSms";
    @Value("${SMS.request.queryParameter.regionId}")
    String regionId = "cn-hangzhou";
    @Value("${SMS.request.queryParameter.signName}")
    String signName="网络教学系统Unit";
    @Value("${SMS.request.queryParameter.templateCode}")
    String templateCode="SMS_190784667";
    @Value("${SMS.module}")
    String module=null;
    {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G5BKxpgNDLgoMMNSSoB", "mRHoZqdukR3J8xpumjCY3V8C4qtPTc");
        this.setClient(new DefaultAcsClient(profile));
    }

    public SMSResponse sendCaptcha(int code,String telephoneNumber) throws ClientException {
        CommonRequest request=new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(getSysDomain());
        request.setSysVersion(getSysVersion());
        request.setSysAction(getSysAction());
        request.putQueryParameter("RegionId", getRegionId());
        request.putQueryParameter("PhoneNumbers", telephoneNumber);
        request.putQueryParameter("SignName", getSignName());
        request.putQueryParameter("TemplateCode", getTemplateCode());
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        CommonResponse response = client.getCommonResponse(request);
        Gson gson=new Gson();
        return gson.fromJson(response.getData(), SMSResponse.class);
    }
}
