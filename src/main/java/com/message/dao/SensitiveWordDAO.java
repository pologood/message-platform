package com.message.dao;

import com.message.entities.SensitiveWord;
import com.message.util.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * Created by hpj
 */
public interface SensitiveWordDAO {


    List<SensitiveWord> querySensitiveWordList(@Param("sensitiveWord") SensitiveWord sensitiveWord, @Param("pager") Pager<SensitiveWord> pager);

    int getSensitiveWordCount(@Param("sensitiveWord")SensitiveWord sensitiveWord);

    Set<String> getAllSensitiveWords();

    void addSensitiveWord(String word);

    void deleteSensitiveWord(long id);
}
