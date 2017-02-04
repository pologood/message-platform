package com.message.service.impl;

import com.message.dao.SmsTemplateDAO;
import com.message.entities.SmsTemplate;
import com.message.service.SmsTemplateService;
import com.message.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hpj on 2015-11-27.
 */
@Service
public class TemplateServiceImpl implements SmsTemplateService {

    @Autowired
    private SmsTemplateDAO smsTemplateDAO;


    @Override
    public Pager<SmsTemplate> queryTemplateList(Pager<SmsTemplate> pager, SmsTemplate template) {
        return null;
    }

    @Override
    public SmsTemplate getTemplateById(Long id) {
        return null;
    }

    @Override
    public SmsTemplate getTemplateByCode(String templateCode) {
        return null;
    }

    @Override
    public SmsTemplate saveOrUpdateTemplate(SmsTemplate template) {
        return null;
    }

    @Override
    public int deleteTemplate(Long id) {
        return 0;
    }
}
