package com.zjs.syncdata.oraclemapper;

import com.zjs.syncdata.model.AddressKeywordOracle;
import com.zjs.syncdata.model.DistributionCenterOracle;
import com.zjs.syncdata.model.ScheduletaskListener;
import com.zjs.syncdata.model.Unit;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author: Liwh
 * @Date: 2018/10/10 9:34
 * @Description:
 */
public interface OracleMapper {

    /**
     * 查询所有关键词总量
     * @return
     */
    int selectCountAddrKeyword();

    /**
     * 根据关键词中的电子围栏ID查询电子围栏表中的单位主键
     * @param centerId
     * @return
     */
    String getIdByUnitPkCorp(@Param("centerId") int centerId);

    /**
     * 分页查询关键词
     */
    List<AddressKeywordOracle> listAddressKeywordOracle();

    /**
     * 查询所有电子围栏总量
     * @return
     */
    int selectCountCenter();

    /**
     * 分页查询电子围栏
     */
    List<DistributionCenterOracle> listCenter();

    /**
     * 根据单位主键查询单位信息
     * @param pkcorp
     * @return
     */
    List<Unit> getUnitByPkCorp(@Param("pkcorp") String pkcorp);

    /**
     * 根据定时任务编码查询状态信息
     * @param code
     * @return
     */
    List<ScheduletaskListener> getStatusByCode(@Param("code") String code);

    /**
     * 根据定时任务编码修改时间及状态为close
     * @param date
     * @param status
     * @param code
     */
    void updateDateAndStatusByCode(@Param("date") Date date, @Param("status") String status, @Param("code") String code);
}
