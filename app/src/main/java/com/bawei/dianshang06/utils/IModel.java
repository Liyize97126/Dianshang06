package com.bawei.dianshang06.utils;

/**
 * Model响应接口
 */
public interface IModel {
    void requestSuccess(String response);
    void requestError(String error);
}
