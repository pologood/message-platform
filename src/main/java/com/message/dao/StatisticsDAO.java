package com.message.dao;

import com.message.entities.ConsumptionRecord;
import com.message.entities.DepositAccount;
import com.message.util.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hpj
 */
public interface StatisticsDAO {
    
    List<ConsumptionRecord> queryConsumptionRecordList(@Param("record") ConsumptionRecord record, @Param("pager") Pager<ConsumptionRecord> pager);

    int getConsumptionRecordCount(@Param("record") ConsumptionRecord record);

    List<DepositAccount> queryDepositList(@Param("depositAccount") DepositAccount depositAccount, @Param("pager") Pager<DepositAccount> pager);

    int getDepositCount(@Param("depositAccount") DepositAccount depositAccount);
}
