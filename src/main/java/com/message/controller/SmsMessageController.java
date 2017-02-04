package com.message.controller;

import com.message.entities.SmsMessage;
import com.message.entities.SensitiveWord;
import com.message.entities.SmsTemplate;
import com.message.entities.User;
import com.message.output.BaseResponse;
import com.message.service.*;
import com.message.util.PageUtils;
import com.message.util.Pager;
import constant.Constant;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hpj
 */
@Controller
@RequestMapping("/smsMessage")
public class SmsMessageController {

    @Autowired
    private SmsMessageService smsMessageService; //用于增删改查

    @Autowired
    private UserService userService;

    @Autowired
    private SmsTemplateService smsTemplateService;

    @Autowired
    private SensitiveWordService sensitiveWordService;

    @Autowired
    private SendService sendService;



    /**
     * to发送短信列表页面
     */
    @RequestMapping(value = "/sendSmsList")
    public String listSMS(SmsMessage request, Model model) {
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.getUserByUserName(userName);

        if (null != user && null != user.getRoles()){
            if(!user.getRoles().contains("ROLE_ADMIN")) {
                request.setUserId(user.getId());
            }
        }
        Pager<SmsMessage> pager = new Pager<>();
        if (null == request.getPageSize()) request.setPageSize("10");
        pager.setCurrentPage(PageUtils.getCorrectCurrentPage(request.getCurrentPage()));
        pager.setPageSize(PageUtils.getCorrectCurrentPageSize(request.getPageSize()));
        pager = smsMessageService.querySMSMessageList(pager, request);
        model.addAttribute("request", request);
        model.addAttribute("pager", pager);
        return "sms/sendSmsList";
    }



    /**
     * to发送短信页面
     */
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String toSendSMS() {
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.getUserByUserName(userName);

        if (null == user || StringUtils.isBlank(user.getRoles())) return "/error"; //用户不存在、用户权限为空 错误
//        if (user.getRoles().indexOf("ADMIN") == -1) return "/unauthorized"; //用户权限不够
        return "/sms/sendSms";
    }


    /**
     * 短信单条/批量发送
     */
    @ResponseBody
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public BaseResponse sendSMS(@RequestBody SmsMessage smsMessage) {
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.getUserByUserName(userName);
        if (null != user){
            smsMessage.setUserId(user.getId());
        }else {
            return new BaseResponse(Constant.FAILED, userName +" 用户不存在",null);}

        smsMessage.setCustomMsg("admin web send,do not need customMsg");
        smsMessage.setSendType("marketing");

        if (smsMessage.getMobile().split(",").length == 1){
            int i = smsMessageService.insertSmsMessage(smsMessage);
            if (i == 1){
                sendService.singleSend(smsMessage);
            }
        }else {
            List<SmsMessage> smsMessageList = new ArrayList<>();
            for (String mobile : smsMessage.getMobile().split(",")){
                SmsMessage message = new SmsMessage();
                BeanUtils.copyProperties(smsMessage,message);
                message.setMobile(mobile);
                smsMessageList.add(message);
            }
            int i = smsMessageService.insertSmsMessageBatch(smsMessageList);
            if (i > 1){
                sendService.batchSend(smsMessage);
            }
        }
        return  new BaseResponse();
    }




    /**
     *  to 模板列表 普通用户可以建立自己的模板，管理员建立的是公用模板
     */
    @RequestMapping(value = "/templateList")
    public String templateList(Model model, SmsTemplate request) {
        if (null != request.getUserName() && ("公用模板").equals(request.getUserName())){
            request.setUserName("admin");
        }
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.getUserByUserName(userName);
        if (null != user && null != user.getRoles()){
            if(!user.getRoles().contains("ROLE_ADMIN")) {
                request.setUserId(user.getId());
            }
        }

        Pager<SmsTemplate> pager = new Pager<>();
        if (null == request.getPageSize()) request.setPageSize("10");
        pager.setCurrentPage(PageUtils.getCorrectCurrentPage(request.getCurrentPage()));
        pager.setPageSize(PageUtils.getCorrectCurrentPageSize(request.getPageSize()));
        pager = smsTemplateService.queryTemplateList(pager, request);

        model.addAttribute("pager", pager);
        model.addAttribute("request", request);
        return "/sms/templateList";
    }



    /**
     * 新增模板页面跳转
     */
    @RequestMapping(value = "/addTemplate")
    public String addTemplate(Model model) {
        SmsTemplate template = new SmsTemplate();
        model.addAttribute("template", template);
        return "/sms/editTemplate";
    }


    /**
     * 新增编辑模板页面跳转
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editTemplate")
    public BaseResponse  editT(@RequestBody SmsTemplate request) {
        BaseResponse baseResponse = new BaseResponse();
        SmsTemplate template = new SmsTemplate();
        if (request.getId() != null) {
            template = smsTemplateService.getTemplateById(request.getId());
        }
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.getUserByUserName(userName);
        if (user.getRoles().contains("ROLE_ADMIN")){
            baseResponse.setStatus("SUCCESS");
            return baseResponse;
        }
        if (!user.getId().equals(template.getUserId())){
            baseResponse.setStatus("FAILED");
            baseResponse.setDescription("您没有权限编辑此模板....");
            return baseResponse;
        }
        baseResponse.setStatus("SUCCESS");
        return baseResponse;
    }


    /**
     * 新增编辑模板页面跳转
     */
    @RequestMapping(value = "/editTemplate/{id}")
    public String  editTemplate(@PathVariable("id")Long id, Model model) {
        SmsTemplate template = new SmsTemplate();
        if (id != null) {
            template = smsTemplateService.getTemplateById(id);
        }
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.getUserByUserName(userName);
        if (!user.getId().equals(template.getUserId()) && !user.getRoles().contains("ROLE_ADMIN")){
            return "/error";
        }
        model.addAttribute("template", template);

        return "/sms/editTemplate";
    }



    /**
     * 保存新增或者编辑短信模板
     */
    @ResponseBody
    @RequestMapping(value = "/saveTemplate", method = RequestMethod.POST)
    public BaseResponse editTemplate(@RequestBody SmsTemplate request) {

        //用正则表达式匹配 匹配出${xxx}
        Pattern pattern = Pattern.compile("\\$\\{((?!\\$).)*}");
        Matcher matcher = pattern.matcher(request.getTemplate());

        int i = 0;
        Set<String> set = new HashSet<>();
        while (matcher.find()){
            i++;
            String key = matcher.group();
            set.add(key);
        }

        request.setTemplate(request.getTemplate().replace("：",":"));
        //新增操作
        if (request.getId() == null){
            String userName = (String) SecurityUtils.getSubject().getPrincipal();
            User user = userService.getUserByUserName(userName);
            request.setUserId(user.getId());
        }
        smsTemplateService.saveOrUpdateTemplate(request);
        BaseResponse response = new BaseResponse();
        response.setStatus("SUCCESS");
        response.setDescription("保存模板成功");
        return response;
    }


    /**
     * 删除模板
     */
    @ResponseBody
    @RequestMapping(value = "/deleteTemplate", method = RequestMethod.POST)
    public BaseResponse deleteTemplate(@RequestBody SmsTemplate smsTemplate) {
        smsTemplateService.deleteTemplate(smsTemplate.getId());
        BaseResponse response = new BaseResponse();
        response.setStatus("success");
        response.setDescription("模板删除成功");
        return response;
    }



    /**
     * @desc  敏感字的列表
     */
    @RequestMapping("/sensitiveWordList")
    public String sensitiveWordList(SensitiveWord request, Model model) {
        Pager<SensitiveWord> pager = new Pager<>();
        if (null == request.getPageSize()) request.setPageSize("10");
        pager.setCurrentPage(PageUtils.getCorrectCurrentPage(request.getCurrentPage()));
        pager.setPageSize(PageUtils.getCorrectCurrentPageSize(request.getPageSize()));
        pager = sensitiveWordService.querySensitiveWordList(request, pager);

        model.addAttribute("request", request);
        model.addAttribute("pager", pager);

        return "/sms/sensitiveWordList";
    }
}
