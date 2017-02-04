package com.message.service;


import com.message.entities.User;
import com.message.util.Pager;

/**
 * Created by hpj
 */
public interface UserService {

    Pager<User> queryUsers(Pager<User> pager, User user);

    void addOrEditUser(User user);

    User getUserById(Long id);

    User getUserByUserName(String userName);

    boolean deleteUser(Long userId);



}
