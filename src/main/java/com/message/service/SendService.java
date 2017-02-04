package com.message.service;

import com.message.entities.SmsMessage;

import java.util.List;

/**
 * Created by hpj
 */
public interface SendService {

    boolean singleSend(SmsMessage message); //单条发送

    boolean batchSend(SmsMessage message);//批量发送

}
