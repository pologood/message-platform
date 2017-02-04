package com.message.controller;

import com.message.entities.User;
import com.message.output.BaseResponse;
import com.message.service.UserService;
import com.message.util.PageUtils;
import com.message.util.Pager;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by hpj
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;




    /**
     * 列出所有用户
     */
    @RequestMapping(value = "/list")
    public String toUserList(User request, Model model) {

        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        User userObj = userService.getUserByUserName(userName);

        if (null != userObj && null != userObj.getRoles()){
            if(userObj.getRoles().contains("ROLE_GENERAL")) {
                request.setId(userObj.getId());
            }
        }

        Pager<User> pager = new Pager<>();
        if (null == request.getPageSize()) request.setPageSize("10");
        pager.setCurrentPage(PageUtils.getCorrectCurrentPage(request.getCurrentPage()));
        pager.setPageSize(PageUtils.getCorrectCurrentPageSize(request.getPageSize()));
        pager = userService.queryUsers(pager, request);

        model.addAttribute("request", request);
        model.addAttribute("pager", pager);
        return "/user/userList";
    }







    /**
     * 跳转到编辑用户页面 077
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String toUserInput(Long id, Model model) {
        if (id == null || id == 0) {return  null;}
        User user = userService.getUserById(id);

        model.addAttribute("user", user);
        return "/user/user_update";
    }



    /**
     * @desc  添加用户 保存至数据库
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResponse save(@RequestBody User request) {
        userService.addOrEditUser(request);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus("SUCCESS");
        baseResponse.setDescription("保存用户成功");
        return baseResponse;
    }



    /**
     * 删除用户
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    public BaseResponse doDeleteUser(@RequestBody User request) {
        userService.deleteUser(request.getId());
        BaseResponse response = new BaseResponse();
        response.setStatus("SUCCESS");
        response.setDescription("删除成功");
        return response;
    }


    /**
     * 余额充值
     */
    @ResponseBody
    @RequestMapping(value = "/topUpBalance")
    public BaseResponse topUpBalance(@RequestBody User request) {
//        userService.topUpBalance(request.getId(), request.getRechargeAmount());
        BaseResponse response = new BaseResponse();
        response.setStatus("SUCCESS");
        response.setDescription("充值成功");
        return response;
    }



    /**
     * 修改密码页面
     */
    @RequestMapping(value = "/toPsdModify", method = RequestMethod.GET)
    public String toPsdModify() {
        return "/user/psdModify";
    }




    /**
     * @desc  重置用户密码 admin才有权限
     */
    @ResponseBody
    @RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
    public BaseResponse resetPwd(@RequestBody User request) {
        Long id = request.getId();
//        userService.resetPwd(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus("SUCCESS");
        baseResponse.setDescription("重置密码成功");
        return baseResponse;
    }




    /**
     * @desc  重置用户密码 admin才有权限
     */
    @ResponseBody
    @RequestMapping(value = "/updateCallbackUrl", method = RequestMethod.POST)
    public BaseResponse updateCallbackUrl(@RequestBody User request) {
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        User userObj = userService.getUserByUserName(userName);
        userObj.setCallbackUrl(request.getCallbackUrl());
        userService.addOrEditUser(userObj);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus("SUCCESS");
        baseResponse.setDescription("更新回调地址成功");
        return baseResponse;
    }







    /**
     * 修改密码
     */
    /*@ResponseBody
    @RequestMapping(value = "/passwordModification", method = RequestMethod.POST)
    public BaseResponse passwordModification(@RequestBody PasswordModificationRequest request) {
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        int result = userService.modifyPassword(userName, request.getOldPassword(), request.getNewPassword());
        BaseResponse response = new BaseResponse();
        if (result == -1) {
            response.setStatus("oldPswError");
            response.setDescription("密码输入错误，请重新输入。");
        } else if (result == 0) {
            response.setStatus("success");
            response.setDescription("密码修改成功，请妥善保管您的新密码");
        }
        return response;
    }*/



}
