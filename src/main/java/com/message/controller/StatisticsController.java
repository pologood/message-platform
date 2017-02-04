package com.message.controller;

import com.message.entities.ConsumptionRecord;
import com.message.entities.DepositAccount;
import com.message.entities.User;
import com.message.service.StatisticsService;
import com.message.service.UserService;
import com.message.util.PageUtils;
import com.message.util.Pager;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zhenwei on 9/7/15.
 */
@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;

    @Autowired
    UserService userService;


    @Value("${max.export.size}")
    private Integer MAX_EXPORT_SIZE;

    /**
     * 查询用户消费记录
     *
     */
    @RequestMapping(value = "/toConsumptionRecordList")
    public String toConsumptionRecordList(ConsumptionRecord dto, Model model) {
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.getUserByUserName(userName);
        if (null != user && null != user.getRoles()){
            if(!user.getRoles().contains("ROLE_ADMIN") && !user.getRoles().contains("ROLE_FINANCE")) {
                dto.setUserId(user.getId());
            }
        }
        Pager<ConsumptionRecord> pager = new Pager<>();
        if (null == dto.getPageSize()) dto.setPageSize("10");
        pager.setCurrentPage(PageUtils.getCorrectCurrentPage(dto.getCurrentPage()));
        pager.setPageSize(PageUtils.getCorrectCurrentPageSize(dto.getPageSize()));
        pager = statisticsService.queryConsumptionRecordList(pager, dto);

        model.addAttribute("request", dto);
        model.addAttribute("pager", pager);
        return "/statistics/consumptionRecordList";
    }

    /**
     * 充值记录
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/toDepositList")
    public String depositList(Model model, DepositAccount request) {
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.getUserByUserName(userName);
        if (null != user && null != user.getRoles()){
            if(!user.getRoles().contains("ROLE_ADMIN") && !user.getRoles().contains("ROLE_FINANCE")) {
                request.setUserId(user.getId());
            }
        }

        Pager<DepositAccount> pager = new Pager<>();
        if (null == request.getPageSize()) request.setPageSize("10");
        pager.setCurrentPage(PageUtils.getCorrectCurrentPage(request.getCurrentPage()));
        pager.setPageSize(PageUtils.getCorrectCurrentPageSize(request.getPageSize()));

        pager = statisticsService.queryDepositList(pager, request);

        model.addAttribute("request", request);
        model.addAttribute("pager", pager);
        return "statistics/depositAccount";
    }

    /**
     * 导出充值记录
     * @param request
     * @param response
     */
    /*@RequestMapping(value = "/deposit/depositDownload", method = RequestMethod.POST)
    public void depositDownload(DepositAccountRequest request, HttpServletResponse response) {
        DepositAccount account = dozerMapper.map(request, DepositAccount.class);
        User user;
        if (null == account.getUserName() || account.getUserName().equals("")){
            String userName = (String) SecurityUtils.getSubject().getPrincipal();
            user = userService.getSimpleUserByUserName(userName);
        }else {
            user = userService.getSimpleUserByUserName(account.getUserName());
        }
        if (null != user && null != user.getRoles()){
            if(!user.getRoles().contains("ROLE_ADMIN") && !user.getRoles().contains("ROLE_FINANCE")) {
                account.setUserId(user.getId());
            }
        }
        List<DepositAccount> list = statisticsService.getExportDeposit(account);
        DepositHSSFWorkbook wb = new DepositHSSFWorkbook();
        wb.paddingCell(list);
        DownloadInfo info = new DownloadInfo();
        info.setFileName("充值记录");
        info.setStartDate(account.getStartDate());
        info.setEndDate(account.getEndDate());
        wb.downloadExcel(response, info, wb.getHssfWorkbook());
    }*/

    /**
     * 导出消费记录
     */
   /* @RequestMapping(value = "/deposit/consumptionDownload", method = RequestMethod.POST)
    public void consumptionDownload(ConsumptionRecordRequest record,HttpServletRequest request, HttpServletResponse response) {
        ConsumptionRecord dto = dozerMapper.map(record, ConsumptionRecord.class);
        User user;
        if (null == dto.getUserName() || dto.getUserName().equals("")){
            String userName = (String) SecurityUtils.getSubject().getPrincipal();
            user = userService.getSimpleUserByUserName(userName);
        }else {
            user = userService.getSimpleUserByUserName(dto.getUserName());
        }
        if (null != user && null != user.getRoles()){
            if(!user.getRoles().contains("ROLE_ADMIN") && !user.getRoles().contains("ROLE_FINANCE")) {
                dto.setUserId(user.getId());
            }
        }
        List<ConsumptionRecord> list = statisticsService.getExportConsumption(dto);
        ConsumptionHSSFWorkbook wb = new ConsumptionHSSFWorkbook();
        wb.paddingCell(list);
        DownloadInfo info = new DownloadInfo();
        info.setFileName("消费记录");
        info.setStartDate(dto.getStartDate());
        info.setEndDate(dto.getEndDate());
        wb.downloadExcel(response, info, wb.getHssfWorkbook());

        *//*int count = statisticsService.getExportConsumptionCount(dto);
        if (count <= MAX_EXPORT_SIZE) {
            doSingleDownload(dto, response, count);
        } else {
            doBatchDownload(dto, request, response, count);
        }*//*
    }*/

    /**
     * 大于指定伐值，导出多个excel的zip包
     * @param dto
     * @param response
     * @param count
     */
    /*private void doBatchDownload(ConsumptionRecord dto, HttpServletRequest request, HttpServletResponse response, int count) {
        Pager<ConsumptionRecord> pager = new Pager<>();
        pager.setPageSize(MAX_EXPORT_SIZE);
        Integer pageCount = count % MAX_EXPORT_SIZE == 0? count / MAX_EXPORT_SIZE : count /MAX_EXPORT_SIZE + 1;
        List<String> fileNameList = new ArrayList<>();
        String tempPath = request.getSession().getServletContext().getRealPath("/temp") + "/";
        String datePeriod = DownloadUtils.createDatePeriodName(dto.getStartDate(), dto.getEndDate());
        for (int i = 1; i <= pageCount; i++) {
            pager.setCurrentPage(i);
            List<ConsumptionRecord> list = statisticsService.queryExportConsumption(pager, dto);
            ConsumptionHSSFWorkbook wb = new ConsumptionHSSFWorkbook();
            wb.paddingCell(list);
            Long timeStamp = DateUtils.getMillis(new Date());
            String filePath = StringUtils.join(tempPath, "消费记录", datePeriod, "_", timeStamp, ".xls");
            wb.createExcelToLocal(wb.getHssfWorkbook(), filePath);
            fileNameList.add(filePath);
        }
        //根据fileName的集合导出zip包
        Long timeStamp = DateUtils.getMillis(new Date());
        String fileName = StringUtils.join("消费记录", datePeriod, "_", timeStamp, ".zip");
        DownloadUtils.createZIP(fileNameList, StringUtils.join(tempPath, fileName));
        ConsumptionHSSFWorkbook.downloadZIP(response, fileNameList, tempPath, fileName);
    }*/

    /**
     * 小于指定伐值，单个文件导出消费记录
     * @param dto
     * @param response
     * @param count
     */
    /*private void doSingleDownload(ConsumptionRecord dto, HttpServletResponse response, int count) {
        Pager<ConsumptionRecord> pager = new Pager<>();
        pager.setPageSize(count);
        List<ConsumptionRecord> list = statisticsService.queryExportConsumption(pager, dto);
        ConsumptionHSSFWorkbook wb = new ConsumptionHSSFWorkbook();
        wb.paddingCell(list);
        DownloadInfo info = new DownloadInfo();
        info.setFileName("充值记录");
        info.setStartDate(dto.getStartDate());
        info.setEndDate(dto.getEndDate());
        wb.downloadExcel(response, info, wb.getHssfWorkbook());
    }*/

}
