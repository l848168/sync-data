package com.zjs.syncdata.model;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @Author: Liwh
 * @Date: 2018/10/8 10:41
 * @Description:
 */
@ToString
@Data
public class DistributionCenterOracle {
    private Integer id;
    private String belongToProvice ;
    private String dCode;
    private String dcCode;
    private String dcName;
    private String areaId;
    private String areaName;
    private String path;
    private BigDecimal minLat;
    private BigDecimal minLng;
    private BigDecimal maxLat;
    private BigDecimal maxLng;
    private String companyId;
    private String center;
    private Date createTime;
    private String creatorId;
    private String creator;
    private Date lastModifyTime;
    private String modifierId;
    private String modifier;
    private String firststagecode;
    private String unitPkcorp;

}
