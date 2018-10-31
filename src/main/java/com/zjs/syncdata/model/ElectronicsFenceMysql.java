package com.zjs.syncdata.model;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: Liwh
 * @Date: 2018/10/10 13:43
 * @Description:
 */
@ToString
@Data
public class ElectronicsFenceMysql {
    private Integer id;
    private String path;
    private BigDecimal min_lat;
    private BigDecimal max_lat;
    private BigDecimal min_lng;
    private BigDecimal max_lng;
    private String center;
    private Integer dr;
    private Date create_time;
    private String creator_id;
    private String creator;
    private String modifier_id;
    private String modifier;
    private Date last_modify_time;
    private String type_code;
    private String unit_pkcorp;
    private String area_id;
}
