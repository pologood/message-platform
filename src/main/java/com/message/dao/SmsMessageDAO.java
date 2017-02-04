package com.message.dao;


import com.message.entities.SmsMessage;
import com.message.entities.SmsReceivingMessage;
import com.message.util.Pager;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by hpj
 */
public interface SmsMessageDAO {

    List<SmsMessage> querySMSMessageList(@Param("messageDTO") SmsMessage messageDTO, @Param("pager") Pager<SmsMessage> pager);

    List<SmsReceivingMessage> querySMSReceivingMessageList(@Param("messageDTO") SmsReceivingMessage messageDTO, @Param("pager") Pager<SmsReceivingMessage> pager);

    int getSMSMessageCount(@Param("messageDTO") SmsMessage messageDTO);

    int getSMSReceivingMessageCount(@Param("messageDTO") SmsReceivingMessage messageDTO);

    SmsMessage getMessageById(@Param("id") Long id);

    int save(SmsMessage message);

    int saveBatch(List<SmsMessage> messageList);

    int update(SmsMessage message);

}
