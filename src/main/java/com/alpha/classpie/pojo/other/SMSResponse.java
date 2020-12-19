package com.alpha.classpie.pojo.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class SMSResponse {
    public static final SMSResponse SUCCESS=new SMSResponse("成功发送");
    public static final SMSResponse REFUSE=new SMSResponse("拒绝发送");
    String Message;
    String RequestId;
    String Code;

    public SMSResponse(String message) {
        Message = message;
    }

    public SMSResponse(String message, String requestId, String code) {
        Message = message;
        RequestId = requestId;
        Code = code;
    }
}
