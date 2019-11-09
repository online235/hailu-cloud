package com.hailu.cloud.api.notify.module.ueditor.component.define;

import com.hailu.cloud.api.notify.module.ueditor.component.Encoder;

import java.util.*;


/**
 * 多状态集合状态
 * 其包含了多个状态的集合, 其本身自己也是一个状态
 *
 * @author Administrator
 */
public class MultiState implements State {

    private boolean state;
    private String info = null;
    private Map<String, Long> intMap = new HashMap<>();
    private Map<String, String> infoMap = new HashMap<>();
    private List<String> stateList = new ArrayList<>();

    public MultiState(boolean state) {
        this.state = state;
    }

    @Override
    public boolean isSuccess() {
        return this.state;
    }

    public void addState(State state) {
        stateList.add(state.toJsonString());
    }

    /**
     * 该方法调用无效果
     */
    @Override
    public void putInfo(String name, String val) {
        this.infoMap.put(name, val);
    }

    @Override
    public String toJsonString() {

        String stateVal = this.isSuccess() ? AppInfo.getStateInfo(AppInfo.SUCCESS) : this.info;

        StringBuilder builder = new StringBuilder();

        builder.append("{\"state\": \"").append(stateVal).append("\"");

        // 数字转换
        Iterator<String> iterator = this.intMap.keySet().iterator();

        while (iterator.hasNext()) {

            stateVal = iterator.next();

            builder.append(",\"").append(stateVal).append("\": ").append(this.intMap.get(stateVal));

        }

        iterator = this.infoMap.keySet().iterator();

        while (iterator.hasNext()) {

            stateVal = iterator.next();

            builder.append(",\"").append(stateVal).append("\": \"").append(this.infoMap.get(stateVal)).append("\"");

        }

        builder.append(", list: [");


        iterator = this.stateList.iterator();

        while (iterator.hasNext()) {

            builder.append(iterator.next()).append(",");

        }

        if (this.stateList.size() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }

        builder.append(" ]}");

        return Encoder.toUnicode(builder.toString());

    }

    @Override
    public void putInfo(String name, long val) {
        this.intMap.put(name, val);
    }

}
