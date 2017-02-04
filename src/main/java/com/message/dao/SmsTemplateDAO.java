package com.message.dao;

import com.message.entities.SmsTemplate;
import com.message.util.Pager;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by hpj on 2015-11-27.
 */
public interface SmsTemplateDAO {

    List<SmsTemplate> queryTemplateList(@Param("template") SmsTemplate template, @Param("pager") Pager<SmsTemplate> pager);

    int getTemplateCount(@Param("template") SmsTemplate template);

    SmsTemplate getTemplateById(@Param("id") Long id);

    SmsTemplate getTemplateByCode(@Param("templateCode") String templateCode);

    void save(@Param("template") SmsTemplate template);

    void update(@Param("template") SmsTemplate template);

    void delete(@Param("id") Long id);
}
