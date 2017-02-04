package com.message.service.impl;

import com.message.dao.MessageSecretDAO;
import com.message.service.MessageSecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xukai on 2016/1/14.
 */
@Service("messageSecretService")
public class MessageSecretServiceImpl implements MessageSecretService {

    @Autowired
    private MessageSecretDAO messageSecretDAO;

    @Override
    public String getSecret(Long id) {
        String secret = messageSecretDAO.getSecret(id);
        return secret;
    }
}
