package com.message.service.impl;

import com.message.dao.UserDAO;
import com.message.entities.User;
import com.message.service.UserService;
import com.message.util.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hpj
 */
@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private UserDAO userDAO;


    @Override
    public Pager<User> queryUsers(Pager<User> pager, User user) {
        if (pager == null) {
            pager = new Pager<>();
        }
        pager.setOrderColumns("create_date"); //时间倒序查询
        List<User> users = userDAO.queryUserList(user, pager);
        int userCount = userDAO.getUserCount(user);
        pager.setList(users);
        pager.setTotalCount(userCount);
        return pager;
    }

    @Override
    public void addOrEditUser(User user) {

    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public User getUserByUserName(String userName) {
        User userByUserName = userDAO.getUserByUserName(userName);
        return userByUserName;
    }

    @Override
    public boolean deleteUser(Long userId) {
        return false;
    }
}
