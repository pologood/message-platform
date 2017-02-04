package com.message.service.impl.sms;

import com.message.entities.SmsMessage;
import com.message.service.SmsMessageService;
import com.message.util.XmlUtils;
import com.message.util.proxy.Callback;
import com.message.util.proxy.HttpClientUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/12/22.
 */
@Service
public class EmpSmsSender {


    private static final Logger logger = LoggerFactory.getLogger(EmpSmsSender.class);

    @Autowired
    private SmsMessageService smsMessageService;


    private String url      = "http://123.56.206.101:8082/MWGate/wmgw.asmx/MongateCsSpSendSmsNew";
    private String userId   = "AIKSYX";
    private String password = "AIKSYX1";

    /**
     * @desc  环信短信消息单发
     * @param message;gateway
     */
    public boolean singleSend(SmsMessage message) {
        logger.info(">>>>>> 环信短信单发 <<<<<<<");
        String params = generateParams(message.getMobile(), message.getContent(), userId, password);
        String result = null;
        try {
            result = HttpClientUtils.getInstance().postForm(url, params, new Callback<String>() {
                @Override
                public String execute(HttpResponse httpResponse, String charset) throws Exception {
                    HttpEntity entity = httpResponse.getEntity();
                    return IOUtils.toString(entity.getContent(), charset);
                }
            });
        } catch (Exception e) {
            logger.error("环信短信发送失败！"+e.getMessage());
            String comment = e.getMessage().length() > 80 ? e.getMessage().substring(0,80) : e.getMessage();
            message.setComment(comment);
            smsMessageService.updateSmsMessage(message);
            return Boolean.FALSE;
        }
        boolean send = true;
        try {
            String singleRootData = XmlUtils.getRootData(result.getBytes(), "UTF-8");
            logger.info("EMP send return code >>> " + singleRootData);
            if (singleRootData.length() < 19){//发送失败 -3907939751134487305 20位
                send = false;
            }
            message.setTraceId(send ? singleRootData : "--");
            message.setStatus(send ? "SUCCESS" : "FAILED");
            message.setComment(send ? "环信发送成功..." + singleRootData : "环信发送失败..." + singleRootData);
            smsMessageService.updateSmsMessage(message);
        } catch (Exception e) {
            logger.error("环信网关返回结果格式报错..."+e.getMessage());
            return Boolean.FALSE;
        }

        return send;
    }




    /**
     * @desc  环信消息群发发
     */
    public boolean batchSend(SmsMessage smsMessage) {
        logger.info(" >>>>>> 环信短信群发 <<<<<<<");
        String params = generateParams(smsMessage.getMobile(), smsMessage.getContent(), userId, password);
        String result = null;
        try {
            result = HttpClientUtils.getInstance().postForm(url, params, new Callback<String>() {
                @Override
                public String execute(HttpResponse httpResponse, String charset) throws Exception {
                    HttpEntity entity = httpResponse.getEntity();
                    return IOUtils.toString(entity.getContent(), charset);
                }
            });
        } catch (Exception e) {
            logger.error("环信短信发送失败！"+e.getMessage());
            String comment = e.getMessage().length() > 80 ? e.getMessage().substring(0,80) : e.getMessage();
//            smsMessageService.updateSmsMessage(message);
            return Boolean.FALSE;
        }

        boolean send = true;
        try {
            String singleRootData = XmlUtils.getRootData(result.getBytes(), "UTF-8");
            logger.info("EMP send return code >>> " + singleRootData);
            if (singleRootData.length() < 19){//发送失败 -3907939751134487305 20位
                send = false;
            }
            smsMessage.setTraceId(send ? singleRootData : "--");
            smsMessage.setStatus(send ? "SUCCESS" : "FAILED");
            smsMessage.setComment(send ? "环信群发发送成功..." + singleRootData : "环信群发发送失败..." + singleRootData);
            smsMessageService.updateSmsMessage(smsMessage);
        } catch (Exception e) {
            logger.error("环信网关返回结果格式报错..."+e.getMessage());
            return Boolean.FALSE;
        }

        return send;
    }



    private String generateParams(String mobile, String content, String userId, String password) {
        StringBuffer params = new StringBuffer();
        params.append("userId=" + userId);
        params.append("&password=" + password);
        params.append("&pszMobis=" + mobile);
        params.append("&pszMsg=" + content);
        params.append("&iMobiCount=" + mobile.split(",").length);
        params.append("&pszMsg=*");
        return params.toString();
    }

}
