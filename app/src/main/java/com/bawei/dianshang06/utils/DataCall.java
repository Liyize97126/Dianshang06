package com.bawei.dianshang06.utils;

import com.bawei.dianshang06.bean.DataBean;

/**
 * 反馈接口
 */
public interface DataCall<T> {
        void success(DataBean<T> data);
        void fail(String error);
}