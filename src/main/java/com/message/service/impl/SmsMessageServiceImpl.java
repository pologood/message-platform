package com.message.service.impl;

import com.message.dao.SmsMessageDAO;
import com.message.entities.SmsMessage;
import com.message.entities.SmsReceivingMessage;
import com.message.service.SmsMessageService;
import com.message.util.Pager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by hpj
 */
@Service
public class SmsMessageServiceImpl implements SmsMessageService {

    private static final Logger logger = LoggerFactory.getLogger(SmsMessageServiceImpl.class);

    @Autowired
    private SmsMessageDAO smsMessageDAO;


    @Override
    public Pager<SmsMessage> querySMSMessageList(Pager<SmsMessage> pager, SmsMessage message) {
        if (pager == null) {
            pager = new Pager<>();
        }
        pager.setOrderColumns("id"); //时间倒序查询
        List<SmsMessage> list = smsMessageDAO.querySMSMessageList(message, pager);
        for (SmsMessage smsMessage : list) {
            String content = smsMessage.getContent();
            if (null != content && content.length() > 30){
                content = content.substring(0,30)+"...";
            }
            smsMessage.setSimContent(content);

            String comment = smsMessage.getComment();
            if (StringUtils.isNotBlank(comment) && comment.length() > 15){
                comment = comment.substring(0,15)+"...";
            }
            smsMessage.setSimComment(comment);
        }
        int count = smsMessageDAO.getSMSMessageCount(message);
        pager.setList(list);
        pager.setTotalCount(count);
        return pager;
    }

    @Override
    public Pager<SmsReceivingMessage> querySMSReceivingMessageList(Pager<SmsReceivingMessage> pager, SmsReceivingMessage message) {
        return null;
    }

    @Override
    public SmsMessage getMessageById(Long id) {
        return null;
    }


    @Override
    public int insertSmsMessage(SmsMessage smsMessage) {
        return smsMessageDAO.save(smsMessage);
    }

    @Override
    public int insertSmsMessageBatch(List<SmsMessage> smsMessageList) {
        return smsMessageDAO.saveBatch(smsMessageList);
    }

    @Override
    public int updateSmsMessage(SmsMessage smsMessage) {
        return smsMessageDAO.update(smsMessage);
    }
}
