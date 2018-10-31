package com.zjs.syncdata.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: Liwh
 * @Date: 2018/10/8 11:31
 * @Description:
 */
@Data
public class ScheduletaskListener {
    private Long id;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date last_execute_date;
    private String status;
    private String code;
    private String name;

}
