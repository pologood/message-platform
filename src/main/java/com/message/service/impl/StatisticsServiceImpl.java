package com.message.service.impl;

import com.message.dao.StatisticsDAO;
import com.message.entities.ConsumptionRecord;
import com.message.entities.DepositAccount;
import com.message.service.StatisticsService;
import com.message.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hpj
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {


    @Autowired
    private StatisticsDAO statisticsDAO;




    @Override
    public Pager<ConsumptionRecord> queryConsumptionRecordList(Pager<ConsumptionRecord> pager, ConsumptionRecord consumptionRecord) {
        if (pager == null) {
            pager = new Pager<>();
        }
        pager.setOrderColumns("create_date"); //时间倒序查询
        List<ConsumptionRecord> consumptionRecords = statisticsDAO.queryConsumptionRecordList(consumptionRecord, pager);
        int consumptionCount = statisticsDAO.getConsumptionRecordCount(consumptionRecord);
        pager.setList(consumptionRecords);
        pager.setTotalCount(consumptionCount);
        return pager;
    }




    @Override
    public Pager<DepositAccount> queryDepositList(Pager<DepositAccount> pager, DepositAccount depositAccount) {

        if (pager == null) {
            pager = new Pager<>();
        }
        pager.setOrderColumns("create_date"); //时间倒序查询
        List<DepositAccount> depositAccounts = statisticsDAO.queryDepositList(depositAccount, pager);
        int depositAccountCount = statisticsDAO.getDepositCount(depositAccount);
        pager.setList(depositAccounts);
        pager.setTotalCount(depositAccountCount);
        return pager;
    }

    @Override
    public boolean saveDepositRecord(DepositAccount depositAccount) {
        return false;
    }

    @Override
    public List<DepositAccount> getExportDeposit(DepositAccount account) {
        return null;
    }

    @Override
    public List<ConsumptionRecord> getExportConsumption(ConsumptionRecord dto) {
        return null;
    }
}
