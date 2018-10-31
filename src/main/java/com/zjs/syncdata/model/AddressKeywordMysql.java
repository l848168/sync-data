package com.zjs.syncdata.model;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: Liwh
 * @Date: 2018/10/8 10:35
 * @Description:
 */
@ToString
@Data
public class AddressKeywordMysql {
    private Integer id;

    private String unit_pkcorp;

    private String keyword;

    private String provice;

    private String city;

    private String county_name;

    private Integer dr;

    private Date create_time;

    private String creator_id;

    private String creator;

    private String modifier_id;

    private String modifier;

    private Date last_modify_time;

    private String corrdinate;

    private BigDecimal lat;

    private BigDecimal lng;

    private Integer status;



}
