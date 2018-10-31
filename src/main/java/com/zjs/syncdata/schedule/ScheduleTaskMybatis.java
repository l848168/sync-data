package com.zjs.syncdata.schedule;

import com.github.pagehelper.PageHelper;
import com.zjs.syncdata.common.BaseResp;
import com.zjs.syncdata.model.*;
import com.zjs.syncdata.mysqlmapper.MysqlMapper;
import com.zjs.syncdata.oraclemapper.OracleMapper;
import com.zjs.syncdata.service.ScheduletaskListenerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Liwh
 * @Date: 2018/10/8 11:25
 * @Description:
 */
@Slf4j
@Component
public class ScheduleTaskMybatis {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");

    private static final String SCHEDULETASK_FLG = "ENABLED";
    public static final String SYNC_ADDRESS_KEYWORD = "syncData";


    @Resource
    private ScheduletaskListenerService scheduletaskListenerService;


    @Resource
    private OracleMapper oracleMapper;
    @Resource
    private MysqlMapper mysqlMapper;

    @Scheduled(cron = "${restrul.syncAddressKeyword}")
    public void syncAddressKeyword() throws Exception {
        if (isEnabled(SYNC_ADDRESS_KEYWORD)) {
            log.info("---------ScheduleTask.java中syncAddressKeyword方法执行调度,开始time={}", format.format(new Date()));
            Long startTime = System.currentTimeMillis();
            int page = 0;
            int pageNumbers = 0;
            final int pageSize = 10000;
            int addrKeyWordCount = oracleMapper.selectCountAddrKeyword();
            if (0 == addrKeyWordCount) {
                log.error("Oracle 关键词的总条数为0，结束执行--------------------------------------------------------");
                return;
            }
            if (0 == addrKeyWordCount % pageSize) {
                pageNumbers = addrKeyWordCount / pageSize;
            } else {
                pageNumbers = (addrKeyWordCount / pageSize) + 1;
            }

            boolean flag = true;
            ArrayList<AddressKeywordMysql> AddressKeywordMysqlList = new ArrayList<>(10002);
            ArrayList<String> list = new ArrayList<>(10002);
            do {
                page++;
                if (pageNumbers == page) {
                    flag = false;
                }
                PageHelper.startPage(page, pageSize,false,false);
                log.info("关键词当前页数："+page+"  关键词总页数："+pageNumbers);
                List<AddressKeywordOracle> addressKeywordOracles = oracleMapper.listAddressKeywordOracle();
                if (null != addressKeywordOracles && addressKeywordOracles.size() > 0) {
                    for (int i = 0; i < addressKeywordOracles.size(); i++) {
                        AddressKeywordOracle addressKeywordOracle =  addressKeywordOracles.get(i);
                        //TODO 根据关键词中的centen_id关联电子围栏中的id得出单位主键
                        AddressKeywordMysql keywordMysql = new AddressKeywordMysql();
                        keywordMysql.setKeyword(addressKeywordOracle.getKeyword());
                        keywordMysql.setProvice(addressKeywordOracle.getProvice());
                        keywordMysql.setCity(addressKeywordOracle.getCity());
                        keywordMysql.setCounty_name(addressKeywordOracle.getCountyName());
                        keywordMysql.setDr(addressKeywordOracle.getStatus());
                        keywordMysql.setCorrdinate(addressKeywordOracle.getCorrdinate());
                        keywordMysql.setLat(addressKeywordOracle.getLat());
                        keywordMysql.setLng(addressKeywordOracle.getLng());
                        keywordMysql.setCreate_time(addressKeywordOracle.getCreateTime());
                        keywordMysql.setCreator(addressKeywordOracle.getCreator());
                        keywordMysql.setCreator_id(addressKeywordOracle.getCreatorId());
                        keywordMysql.setModifier_id(addressKeywordOracle.getModifierId());
                        keywordMysql.setModifier(addressKeywordOracle.getModifier());
                        keywordMysql.setLast_modify_time(addressKeywordOracle.getLastModifyTime());
                        keywordMysql.setStatus(1);
                        int centerId = addressKeywordOracle.getCenterId();
                        String unitPkCorp = oracleMapper.getIdByUnitPkCorp(centerId);
                        if (null == unitPkCorp || "".equals(unitPkCorp)) {
                            list.add(addressKeywordOracle.getKeyword()+"=="+centerId+"=="+unitPkCorp);
                        }
                        keywordMysql.setUnit_pkcorp(unitPkCorp);
                        AddressKeywordMysqlList.add(keywordMysql);
                    }
                    for (int i = 0; i < list.size(); i++) {
                        System.out.print(list.get(i)+" ");
                    }
                    mysqlMapper.saveAddrKeyWord(AddressKeywordMysqlList);
                    AddressKeywordMysqlList.clear();
                }

            } while (flag);
            AddressKeywordMysqlList = null;
            scheduletaskListenerService.updateByCode(SYNC_ADDRESS_KEYWORD, new Date());
            Long endTime = System.currentTimeMillis();
            log.info("---------ScheduleTask.java中syncAddressKeyword方法执行调度结束,耗时={}秒", (endTime - startTime) * 1.0/1000);
            //同步电子围栏数据
            syncElectronicsFence();
        } else {
            log.info("=========syncAddressKeyword对应的监听器状态为关闭状态或未设置监听器信息!");
        }
    }

    private void syncElectronicsFence() throws Exception{
        log.info("---------同步电子围栏数据开始，开始time={}", format.format(new Date()));
        Long startTime = System.currentTimeMillis();
        int page = 0;
        int pageNumbers = 0;
        final int pageSize = 10000;
        int centerCount = oracleMapper.selectCountCenter();
        if (0 == centerCount) {
            log.error("Oracle 电子围栏的总条数为0，结束执行--------------------------------------------------------");
            return;
        }
        if (0 == centerCount % pageSize) {
            pageNumbers = centerCount / pageSize;
        } else {
            pageNumbers = (centerCount / pageSize) + 1;
        }
        boolean flag = true;
        List<ElectronicsAttributesMysql> ElectronicsAttributesMysqlList = new ArrayList<>(10002);
        do {
            page++;
            if (pageNumbers == page) {
                flag = false;
            }
            PageHelper.startPage(page, pageSize,false,false);
            log.info("电子围栏当前页数："+page+"  电子围栏总页数："+pageNumbers);
            List<DistributionCenterOracle> distributionCenterOracles = oracleMapper.listCenter();
            if (null != distributionCenterOracles && distributionCenterOracles.size() > 0) {
                for (int i = 0; i < distributionCenterOracles.size(); i++) {
                    DistributionCenterOracle distributionCenterOracle =  distributionCenterOracles.get(i);
                    ElectronicsFenceMysql electronicsFenceMysql = new ElectronicsFenceMysql();
                    electronicsFenceMysql.setPath(distributionCenterOracle.getPath());
                    electronicsFenceMysql.setMin_lat(distributionCenterOracle.getMinLat());
                    electronicsFenceMysql.setMax_lat(distributionCenterOracle.getMaxLat());
                    electronicsFenceMysql.setMin_lng(distributionCenterOracle.getMinLng());
                    electronicsFenceMysql.setMax_lng(distributionCenterOracle.getMaxLng());
                    electronicsFenceMysql.setCenter(distributionCenterOracle.getCenter());
                    electronicsFenceMysql.setDr(1);
                    electronicsFenceMysql.setCreate_time(distributionCenterOracle.getCreateTime());
                    electronicsFenceMysql.setCreator_id(distributionCenterOracle.getCreatorId());
                    electronicsFenceMysql.setCreator(distributionCenterOracle.getCreator());
                    electronicsFenceMysql.setModifier_id(distributionCenterOracle.getModifierId());
                    electronicsFenceMysql.setModifier(distributionCenterOracle.getModifier());
                    electronicsFenceMysql.setLast_modify_time(distributionCenterOracle.getLastModifyTime());
                    String unit_pkcorp = distributionCenterOracle.getUnitPkcorp();
                    electronicsFenceMysql.setUnit_pkcorp(unit_pkcorp);
                    electronicsFenceMysql.setArea_id(distributionCenterOracle.getAreaId());

                    //保存电子围栏
                    mysqlMapper.saveElectronicsFence(electronicsFenceMysql);
                    //插入成功后返回主键id
                    int id = electronicsFenceMysql.getId();
                    //根据单位主键查询单位信息
                    List<Unit> units = oracleMapper.getUnitByPkCorp(unit_pkcorp);

                    if (null != units && units.size() > 0) {
                        for (int j = 0; j < units.size(); j++) {
                            Unit unit =  units.get(j);
                            ElectronicsAttributesMysql attributesMysql = new ElectronicsAttributesMysql();
                            attributesMysql.setS_code(distributionCenterOracle.getDCode());
                            attributesMysql.setUnit_pkcorp(unit_pkcorp);
                            attributesMysql.setUnit_code(unit.getUnitcode());
                            attributesMysql.setUnit_name(unit.getUnitname());
                            attributesMysql.setFence_id(id);
                            attributesMysql.setFence_code(distributionCenterOracle.getDcCode());
                            attributesMysql.setFence_name(distributionCenterOracle.getDcName());
                            attributesMysql.setF_code(distributionCenterOracle.getFirststagecode());
                            attributesMysql.setDr(1);
                            attributesMysql.setCreate_time(distributionCenterOracle.getCreateTime());
                            attributesMysql.setCreator_id(distributionCenterOracle.getCreatorId());
                            attributesMysql.setCreator(distributionCenterOracle.getCreator());
                            attributesMysql.setModifier_id(distributionCenterOracle.getModifierId());
                            attributesMysql.setModifier(distributionCenterOracle.getModifier());
                            attributesMysql.setLast_modify_time(distributionCenterOracle.getLastModifyTime());
                            ElectronicsAttributesMysqlList.add(attributesMysql);
                        }
                    }

                }
                mysqlMapper.saveElectronicsAttributes(ElectronicsAttributesMysqlList);
                ElectronicsAttributesMysqlList.clear();
            }
        }while(flag);
        ElectronicsAttributesMysqlList = null;
        Long endTime = System.currentTimeMillis();
        log.info("---------同步电子围栏数据结束,耗时={}秒", (endTime - startTime) * 1.0/1000);
    }


    private boolean isEnabled(String code) throws Exception {
        BaseResp<List<ScheduletaskListener>> baseResp = scheduletaskListenerService.queryByIdAndCode(code);

        List<ScheduletaskListener> stlList = baseResp.getData();
        if (stlList == null || stlList.size() == 0) {
            log.error("根据{}查询出来的记录为空!", code);
            return false;
        } else if (stlList.size() > 1) {
            log.error("根据{}查询出来的记录有多条!", code);
            return false;
        }

        ScheduletaskListener scheduletaskListener = stlList.get(0);
        if (scheduletaskListener == null) {
            return false;
        } else {
            String status = scheduletaskListener.getStatus();
            if (SCHEDULETASK_FLG.equals(status)) {
                return true;
            }
            return false;
        }
    }
}
