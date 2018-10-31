package com.zjs.syncdata.service;

import com.zjs.syncdata.common.BaseResp;
import com.zjs.syncdata.model.ScheduletaskListener;

import java.util.Date;
import java.util.List;

/**
 * @Author: Liwh
 * @Date: 2018/10/8 11:29
 * @Description:
 */
public interface ScheduletaskListenerService {
    BaseResp<List<ScheduletaskListener>> queryByIdAndCode(String code) throws Exception;

    void updateByCode(String code, Date date) throws Exception;


}
