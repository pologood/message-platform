package com.message.service.impl;

import com.message.entities.SmsMessage;
import com.message.service.SendService;

import com.message.service.impl.sms.AlidayuSmsSender;
import com.message.service.impl.sms.EmpSmsSender;
import constant.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hpj
 */
@Service
public class SendServiceImpl implements SendService {

    @Autowired
    private AlidayuSmsSender alidayuSmsSender;

    @Autowired
    private EmpSmsSender empSmsSender;


    @Override
    public boolean singleSend(SmsMessage message) {
        boolean result = false;
        if (StringUtils.isEmpty(message.getSendType())) return result;
        if (message.getSendType().equals(Constant.marketing)){
            result = empSmsSender.singleSend(message);
        }else if (message.getSendType().equals(Constant.verify)){
            result = empSmsSender.singleSend(message);
        }
        return result;
    }

    @Override
    public boolean batchSend(SmsMessage message) {
        boolean result = false;
        if (StringUtils.isEmpty(message.getSendType())) return result;

        if (message.getSendType().equals(Constant.marketing)){
            result = empSmsSender.batchSend(message);

        }else if (message.getSendType().equals(Constant.verify)){
            result = empSmsSender.batchSend(message);

        }
        return result;
    }
}
