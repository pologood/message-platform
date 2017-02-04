package com.message.dao;

import com.message.entities.Channel;
import com.message.util.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hpj
 */
public interface ChannelDAO {


    List<Channel> queryChannelList(@Param("channel") Channel channel, @Param("pager") Pager<Channel> pager);

    Channel getChannelById(@Param("id") Long id);

    Channel getChannelByName(@Param("channelName") String channelName);

    int getChannelCount(@Param("channel") Channel channel);

    int save(@Param("channel") Channel channel);

    int update(@Param("channel") Channel channel);

    int delete(Long id);

}