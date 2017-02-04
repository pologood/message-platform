package com.message.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hpj on 2015-11-24.
 */
public class KeywordFilterUtil {

    public static int minMatchTYpe = 1;

    /**
     * 敏感词库
     */
    public static HashMap sensitiveWordMap = null;

    /**
     * 检查敏感词
     *
     * @return
     */
    public static List<String> checkKeyword(String txt, int matchType) {
        List<String> sensitiveWordList = new ArrayList<String>();
        for (int i = 0; i < txt.length(); i++) {
            int length = checkKeyword(txt, i, matchType);
            if (length > 0) {
                sensitiveWordList.add(txt.substring(i, i + length));
                i = i + length - 1;
            }
        }

        return sensitiveWordList;
    }

    /**
     * 格式化多个敏感词的格式
     *
     * @param keywordList
     * @return
     */
    public static String formatKeywordList(List<String> keywordList) {
        if (keywordList == null || keywordList.size() == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (String keyword : keywordList) {
            if (sb.length() > 0) sb.append(",");
            sb.append(keyword);
        }
        return sb.toString();
    }

    /**
     * 敏感词检测
     *
     * @param txt
     * @param beginIndex 开始检测的位置
     * @param matchType  最小匹配字数
     * @return
     */
    private static int checkKeyword(String txt, int beginIndex, int matchType) {
        boolean flag = false;
        int matchFlag = 0;
        char word;
        Map nowMap = sensitiveWordMap;
        for (int i = beginIndex; i < txt.length(); i++) {
            word = txt.charAt(i);
            nowMap = (Map) nowMap.get(word);
            if (nowMap != null) {
                matchFlag++;
                if ("true".equals(nowMap.get("isEnd"))) {
                    flag = true;
                    if (KeywordFilterUtil.minMatchTYpe == matchType) {
                        break;
                    }
                }
            } else {
                break;
            }
        }
        if (!flag) {
            matchFlag = 0;
        }
        return matchFlag;
    }

}
