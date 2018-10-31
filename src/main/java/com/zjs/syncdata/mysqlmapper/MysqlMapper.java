package com.zjs.syncdata.mysqlmapper;

import com.zjs.syncdata.model.AddressKeywordMysql;
import com.zjs.syncdata.model.ElectronicsAttributesMysql;
import com.zjs.syncdata.model.ElectronicsFenceMysql;

import java.util.List;

/**
 * @Author: Liwh
 * @Date: 2018/10/10 9:34
 * @Description:
 */
public interface MysqlMapper {

    /**
     * 批量保存关键词
     * @param addressKeywordMysql
     */
    void saveAddrKeyWord(List<AddressKeywordMysql> addressKeywordMysql);

    /**
     * 批量保存电子围栏
     * @param electronicsFenceMysql
     */
    void saveElectronicsFence(ElectronicsFenceMysql electronicsFenceMysql);

    /**
     * 批量保存电子围栏属性
     * @param electronicsAttributesMysql
     */
    void saveElectronicsAttributes(List<ElectronicsAttributesMysql> electronicsAttributesMysql);

}
