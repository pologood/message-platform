package com.message.service;


import com.message.entities.ConsumptionRecord;
import com.message.entities.DepositAccount;
import com.message.util.Pager;

import java.util.List;

/**
 * Created by hpj
 */
public interface StatisticsService {

    //查询消费纪录
    Pager<ConsumptionRecord> queryConsumptionRecordList(Pager<ConsumptionRecord> pager, ConsumptionRecord dto);
    //查询充值纪录
    Pager<DepositAccount> queryDepositList(Pager<DepositAccount> pager, DepositAccount depositAccount);
    //保存充值纪录
    boolean saveDepositRecord(DepositAccount depositAccount);
    //导出充值纪录
    List<DepositAccount> getExportDeposit(DepositAccount account);
    //导出消费纪录
    List<ConsumptionRecord> getExportConsumption(ConsumptionRecord dto);


}
