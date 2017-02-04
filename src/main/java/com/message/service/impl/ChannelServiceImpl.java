package com.message.service.impl;

import com.message.dao.ChannelDAO;
import com.message.entities.Channel;
import com.message.service.ChannelService;
import com.message.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by hpj
 */
@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelDAO channelDAO;


    @Override
    public Pager<Channel> queryChannelList(Channel channel, Pager<Channel> pager) {
        if (pager == null) {
            pager = new Pager<>();
        }
        pager.setOrderColumns("create_date"); //时间倒序查询
        List<Channel> channels = channelDAO.queryChannelList(channel, pager);
        int channelsCount = channelDAO.getChannelCount(channel);
        pager.setList(channels);
        pager.setTotalCount(channelsCount);
        return pager;
    }

    @Override
    public Channel addOrEditChannel(Channel channel) {
        return null;
    }

    @Override
    public Channel getChannelByName(String channelName) {
        return null;
    }

    @Override
    public Channel getChannelById(Long id) {
        return null;
    }

    @Override
    public boolean deleteChannel(Long id) {
        return false;
    }
}
