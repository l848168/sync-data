package com.zjs.syncdata.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Liwh
 * @Date: 2018/10/8 11:32
 * @Description:
 */
@Data
public class BaseResp<T extends Object> implements Serializable {

    public static int SUCCESS = 0;

    public static int FAIlURE = -1;

    //成功直接返回 BaseResp.ok()
    public static BaseResp ok() {

        return new BaseResp(BaseResp.SUCCESS, "操作成功!");
    }
    //成功直接返回 BaseResp.ok(msg)
    public static BaseResp ok(String msg) {

        return new BaseResp(BaseResp.SUCCESS, msg);
    }

    //失败直接返回 BaseResp.fail()
    public static BaseResp fail() {

        return new BaseResp(BaseResp.FAIlURE, "服务器异常!");
    }

    //失败直接返回 BaseResp.fail(msg)
    public static BaseResp fail(String msg) {

        return new BaseResp(BaseResp.FAIlURE, msg);
    }

    private int code;

    private T data;

    private String description;

    private String downloadkey;

    private Boolean beRefresh;

    private long total;

    public BaseResp() {

    }

    public BaseResp(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public BaseResp(T data) {
        this.code = SUCCESS;
        this.data = data;
    }


}
