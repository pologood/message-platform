package com.message.service;


import com.message.entities.SmsMessage;
import com.message.entities.SmsReceivingMessage;
import com.message.util.Pager;

import java.util.List;
import java.util.Map;

/**
 * Created by hpj
 */
public interface SmsMessageService {

    Pager<SmsMessage> querySMSMessageList(Pager<SmsMessage> pager, SmsMessage message); //查询发送短信列表

    Pager<SmsReceivingMessage> querySMSReceivingMessageList(Pager<SmsReceivingMessage> pager, SmsReceivingMessage message); //查询接受担心列表

    SmsMessage getMessageById(Long id); //查询短信根据ID

    int insertSmsMessage(SmsMessage smsMessage);

    int insertSmsMessageBatch(List<SmsMessage> smsMessageList);

    int updateSmsMessage(SmsMessage smsMessage);



}
