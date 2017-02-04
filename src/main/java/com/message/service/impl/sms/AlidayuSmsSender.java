package com.message.service.impl.sms;

import com.message.entities.SmsMessage;
import com.message.service.SmsMessageService;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by hpj
 */
@Service
public class AlidayuSmsSender {

    @Autowired
    private SmsMessageService smsMessageService;


    private static final Logger logger = LoggerFactory.getLogger(AlidayuSmsSender.class);
    private static final String url = "";
    private static final String appkey = "";
    private static final String secret = "";


    /**
     * @desc  阿里大鱼消息单发
     * @param message;gateway
     */
    public boolean singleSend(SmsMessage message) {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
//        req.setExtend("123456");
        req.setSmsType("normal");
        req.setSmsFreeSignName("爱客仕");
        req.setSmsParamString(message.getTemplateParam());
        req.setRecNum(message.getMobile());
        req.setSmsTemplateCode("");
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (ApiException e) {
            logger.error("阿里大于发送失败");
            String comment = e.getErrMsg().length() > 80 ? e.getErrMsg().substring(0,80) : e.getErrMsg();
            message.setComment(comment);
            smsMessageService.updateSmsMessage(message);
            return Boolean.FALSE;
        }
        if (rsp.getResult() != null && rsp.getResult().getSuccess()){
            message.setStatus(Constant.SUCCESS);
        }else {
            message.setStatus(Constant.FAILED);
        }
        message.setComment(rsp.getSubMsg());
        smsMessageService.updateSmsMessage(message);
        return Boolean.TRUE;
    }


    /**
     * @desc  阿里大于消息群发发
     */
    public boolean batchSend(SmsMessage message) {
        logger.info("阿里大于群发 >>>> 数量 : ");
//        for (SmsMessage message: list) {
//            singleSend(message);
//        }
        return Boolean.TRUE;
    }

}
