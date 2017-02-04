package com.message.service;


import com.message.entities.SmsTemplate;
import com.message.util.Pager;

import java.util.List;

/**
 * Created by hpj on 2015-11-27.
 */
public interface SmsTemplateService {

    Pager<SmsTemplate> queryTemplateList(Pager<SmsTemplate> pager, SmsTemplate template); //查询模板

    SmsTemplate getTemplateById(Long id); //根据ID查询模板

    SmsTemplate getTemplateByCode(String templateCode);

    SmsTemplate saveOrUpdateTemplate(SmsTemplate template);

    int deleteTemplate(Long id);
}
