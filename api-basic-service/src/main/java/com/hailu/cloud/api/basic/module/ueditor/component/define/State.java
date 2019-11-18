package com.hailu.cloud.api.basic.module.ueditor.component.define;

/**
 * 处理状态接口
 *
 * @author hancong03@baidu.com
 */
public interface State {

    /**
     * 是否处理成功
     *
     * @return
     */
    boolean isSuccess();

    /**
     * 返回数据
     *
     * @param name
     * @param val
     */
    void putInfo(String name, String val);

    /**
     * 返回数据
     *
     * @param name
     * @param val
     */
    void putInfo(String name, long val);

    /**
     * 转换成json
     *
     * @return
     */
    String toJsonString();

}
