package com.message.service.impl;

import com.message.dao.SensitiveWordDAO;
import com.message.dao.SmsMessageDAO;
import com.message.entities.SensitiveWord;
import com.message.service.SensitiveWordService;
import com.message.util.KeywordFilterUtil;
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
public class SensitiveWordServiceImpl implements SensitiveWordService {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveWordServiceImpl.class);


    @Autowired
    private SensitiveWordDAO sensitiveWordDAO;

    /**
     * 检测是否存在敏感词，如果存在抛出异常并提示存在哪些敏感词
     */
    public String validateKeyword(String words){
        if (StringUtils.isBlank(words)){return null;}
        if (KeywordFilterUtil.sensitiveWordMap == null || KeywordFilterUtil.sensitiveWordMap.isEmpty()) {
            KeywordFilterUtil.sensitiveWordMap = getAllSensitiveWords();
        }
        List<String> list = KeywordFilterUtil.checkKeyword(words, 1);

        return KeywordFilterUtil.formatKeywordList(list);
    }



    /**
     * 查询敏感词
     */
    @Override
    public List<String> queryInvalidKeyword(String content) {
        if (KeywordFilterUtil.sensitiveWordMap == null || KeywordFilterUtil.sensitiveWordMap.isEmpty()) {
            KeywordFilterUtil.sensitiveWordMap = getAllSensitiveWords();
        }
        return KeywordFilterUtil.checkKeyword(content, 1);
    }



    /**增加敏感字 * */
    public void insertSensitiveWord(String sensitiveWord){
        Set<String> allWords = sensitiveWordDAO.getAllSensitiveWords();
        String[] words = sensitiveWord.split(",");
        if (words.length < 1) {
            return;
        }

        //数组去重复数据
        List<String> list = new ArrayList<>();
        for (String word : words) {
            if (!list.contains(word)) {
                list.add(word);
            }
        }
        String[] distinctWords = list.toArray(new String[0]);

        //检测敏感字
        for (String word : distinctWords) {
            for (String s : allWords) {
                if (s.equals(word)) {
//                    throw new XBusinessException("添加失败,敏感字 "+word+ " 已存在!");
                }
            }
        }
        for (String word : distinctWords) {
            sensitiveWordDAO.addSensitiveWord(word);
        }

        if (KeywordFilterUtil.sensitiveWordMap != null){
            KeywordFilterUtil.sensitiveWordMap = null;
        }

    }



    /**敏感词查询*/
    public Pager<SensitiveWord> querySensitiveWordList(SensitiveWord sensitiveWord, Pager<SensitiveWord> pager){
        if (pager == null) {
            pager = new Pager<>();
        }
        pager.setOrderColumns("id");
        List<SensitiveWord> list = sensitiveWordDAO.querySensitiveWordList(sensitiveWord, pager);
        int count = sensitiveWordDAO.getSensitiveWordCount(sensitiveWord);
        pager.setList(list);
        pager.setTotalCount(count);
        return pager;
    }


    /**
     * @desc  删除敏感字
     * @param id
     */
    @Override
    public void deleteSensitiveWord(long id) {
        sensitiveWordDAO.deleteSensitiveWord(id);
        if (KeywordFilterUtil.sensitiveWordMap != null){
            KeywordFilterUtil.sensitiveWordMap = null;
        }
    }



    /**  查询所有敏感字  */
    private HashMap getAllSensitiveWords() {
        Set<String> keyWords =  sensitiveWordDAO.getAllSensitiveWords();
        HashMap sensitiveWordMap = new HashMap<>(keyWords.size());
        for (String keyWord : keyWords) {
            createKeyWord(sensitiveWordMap, keyWord.trim());
        }
        return sensitiveWordMap;
    }


    /** 构建敏感词库  */
    private void createKeyWord(Map sensitiveWordMap, String keyWord) {
        if (sensitiveWordMap == null) {
            logger.error("sensitiveWordMap 未初始化!");
            return;
        }
        Map nowMap;
        Map<String, String> newWorMap;
        nowMap = sensitiveWordMap;
        for(int i = 0 ; i < keyWord.length() ; i++){
            char keyChar = keyWord.charAt(i);
            Object wordMap = nowMap.get(keyChar);

            if(wordMap != null){
                nowMap = (Map) wordMap;
            }
            else{
                newWorMap = new HashMap<>();
                newWorMap.put("isEnd", "false");
                nowMap.put(keyChar, newWorMap);
                nowMap = newWorMap;
            }
            if(i == keyWord.length() - 1){
                nowMap.put("isEnd", "true");
            }
        }
    }
}
