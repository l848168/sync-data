package com.zjs.syncdata.model;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: Liwh
 * @Date: 2018/9/30 16:03
 * @Description:
 */
@ToString
@Data
public class AddressKeywordOracle {
    private Integer id;

    private String companyId;

    private String keyword;

    private String provice;

    private String city;

    private String countyName;

    private Integer status;

    private Date createTime;

    private String creatorId;

    private String creator;

    private String modifierId;

    private String modifier;

    private Date lastModifyTime;

    private String corrdinate;

    private BigDecimal lat;

    private BigDecimal lng;

    /** TYZ 厅点id */
    private Integer centerId;
}
