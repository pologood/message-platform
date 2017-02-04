package com.message.dao;

import com.message.entities.User;
import com.message.util.Pager;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by hpj
 */
public interface UserDAO {

    List<User> queryUserList(@Param("user") User user, @Param("pager") Pager<User> pager);

    int getUserCount(@Param("user") User user);

    User getUserByUserName(@Param("userName") String userName);

    void save(@Param("user") User user);

    boolean update(@Param("user") User user);

    boolean delete(@Param("userId") Long userId);

}
