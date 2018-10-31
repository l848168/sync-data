package com.zjs.syncdata.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: Liwh
 * @Date: 2018/10/10 13:46
 * @Description:
 */
@ToString
@Data
public class ElectronicsAttributesMysql {
    private Integer id;
    private String s_code;
    private String unit_pkcorp;
    private String unit_code;
    private String unit_name;
    private Integer fence_id;
    private String fence_code;
    private String fence_name;
    private String f_code;
    private Integer dr;
    private Date create_time;
    private String creator_id;
    private String creator;
    private String modifier_id;
    private String modifier;
    private Date last_modify_time;

}
