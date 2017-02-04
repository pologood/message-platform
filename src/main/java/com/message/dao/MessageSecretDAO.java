package com.message.dao;

import org.apache.ibatis.annotations.Param;

/**
 * Created by hpj
 */
public interface MessageSecretDAO {

    String getSecret(@Param("id") Long id);

}


