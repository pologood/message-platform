package com.message.service;

import com.message.entities.SensitiveWord;
import com.message.util.Pager;

import java.util.List;

/**
 * @author  hpj
 * @desc 短信关键词
 */
public interface SensitiveWordService {

    //校验敏感词
    String validateKeyword(String words);

    /**查询敏感词*/
    List<String> queryInvalidKeyword(String content);

    /**增加敏感字*/
    void insertSensitiveWord(String  sensitiveWord);

    /**敏感词查询*/
    Pager<SensitiveWord> querySensitiveWordList(SensitiveWord sensitiveWord, Pager<SensitiveWord> pager);

    void deleteSensitiveWord(long id);
}
