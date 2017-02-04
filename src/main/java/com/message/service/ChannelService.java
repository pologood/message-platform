package com.message.service;



import com.message.entities.Channel;
import com.message.util.Pager;

/**
 * Created by hpj
 */
public interface ChannelService {

    Pager<Channel> queryChannelList(Channel channel, Pager<Channel> pager);

    Channel addOrEditChannel(Channel channel);  //新增编辑channel

    Channel getChannelByName(String channelName); //根据名称获取channel

    Channel getChannelById(Long id); //根据id获取channel

    boolean deleteChannel(Long id); //根据id删除channel

}
