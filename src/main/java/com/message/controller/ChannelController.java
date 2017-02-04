package com.message.controller;

import com.message.entities.Channel;
import com.message.entities.User;
import com.message.output.BaseResponse;
import com.message.service.ChannelService;
import com.message.service.UserService;
import com.message.util.PageUtils;
import com.message.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhenwei on 9/30/15.
 */
@Controller
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private UserService userService;


    /**
     * 通道列表页
     */
    @RequestMapping(value = "/list")
    public String toChannelList(Channel request, Model model) {
        Pager<Channel> pager = new Pager<>();
        if (null == request.getPageSize()) request.setPageSize("10");
        pager.setCurrentPage(PageUtils.getCorrectCurrentPage(request.getCurrentPage()));
        pager.setPageSize(PageUtils.getCorrectCurrentPageSize(request.getPageSize()));
        pager = channelService.queryChannelList(request, pager);
        model.addAttribute("request", request);
        model.addAttribute("pager", pager);
        return "/channel/channelList";
    }



    /**
     * @desc 跳转到添加通道页面
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addChannel(Model model) {
        List<String> typeList = new ArrayList<>();
        typeList.add("AMS");
        typeList.add("SMS_MARKETING");
        typeList.add("SMS_VERIFY");
        model.addAttribute("typeList",typeList);
        return "/channel/channel_add";
    }




    /**
     * @desc 添加通道保存到数据库
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResponse saveChannel(@RequestBody Channel request) {
        channelService.addOrEditChannel(request);
        return new BaseResponse("SUCCESS","保存通道成功",null);
    }



    /**
     * 通道删除
     */
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public BaseResponse doDeleteChannel(@RequestBody Channel request) {
        channelService.deleteChannel(request.getId());
        return new BaseResponse("SUCCESS","删除成功",null);
    }


}
