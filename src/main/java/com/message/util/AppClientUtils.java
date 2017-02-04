package com.message.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hpj on 2015-08-31.
 */
public class AppClientUtils {

    /**
     * 根据,来分割string，得到clientId集合
     *
     * @param ids
     * @return
     */
    public static List<String> getClientIdList(String ids) {
        if (StringUtils.isEmpty(ids)) {
            return null;
        }
        String[] split = ids.trim().split(",");
        return Arrays.asList(split);
    }

    /**
     * 验证传入的clientId是否合法
     *
     * @param list
     * @return
     */
    public static boolean validClientId(List<String> list) {
        for (String id : list) {
            if (StringUtils.isEmpty(id) || id.length() > 50) {
                return false;
            }
        }
        return true;
    }

    /**
     * 确认用户传入的clientId不能重复
     *
     * @param clientList
     * @return
     */
    public static boolean ensureClientSetNoRepeat(List<String> clientList) {
        Set set = new HashSet(clientList);
        return set.size() == clientList.size();
    }

}
