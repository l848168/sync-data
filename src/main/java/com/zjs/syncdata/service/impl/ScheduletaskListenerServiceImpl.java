package com.zjs.syncdata.service.impl;

import com.zjs.syncdata.common.BaseResp;
import com.zjs.syncdata.model.ScheduletaskListener;
import com.zjs.syncdata.oraclemapper.OracleMapper;
import com.zjs.syncdata.service.ScheduletaskListenerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: Liwh
 * @Date: 2018/10/8 11:33
 * @Description:
 */
@Service
public class ScheduletaskListenerServiceImpl implements ScheduletaskListenerService {

    @Resource
    private OracleMapper oracleMapper;


    @Override
    public BaseResp<List<ScheduletaskListener>> queryByIdAndCode(String code) throws Exception {
        BaseResp<List<ScheduletaskListener>> baseResp = new BaseResp();
        List<ScheduletaskListener> stlList = oracleMapper.getStatusByCode(code);
        baseResp.setData(stlList);
        if (stlList == null || stlList.size() == 0) {
            baseResp.setDescription("数据有误!");
            baseResp.setCode(BaseResp.FAIlURE);
        } else {
            baseResp.setDescription("查询成功!");
            baseResp.setCode(BaseResp.SUCCESS);
        }
        return baseResp;
    }

    @Override
    public void updateByCode(String code, Date date) throws Exception {
        if (StringUtils.isEmpty(code)) {
            throw new Exception("code不能为空!");
        }
        if (date == null) {
            date = new Date();
        }
        oracleMapper.updateDateAndStatusByCode(date, "DISABLED", code);
    }
}
