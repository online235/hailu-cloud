package com.hailu.cloud.common.fill;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import com.hailu.cloud.common.model.page.PageInfoModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 字典项自动填充，目前仅处理最大深度3级以内的数据, 防止引发性能下降
 * 后续压测看结果，如果性能略低，降到2级以内
 * 单机环境下目前限制分页数据最大查询200条以内的3级深度，大概20ms上下的注入速度，并发环境下应该能控制在500毫秒内
 * 单机环境下2000条以内的3级深度，大概60ms上下的注入速度，并发环境下应该可能会去到1~2秒，包括gc延迟，不过一般也不会返回如此巨大的数据量
 * 单机环境下20000条以内的3级深度，大概270~300ms上下的注入速度，并发环境下可能会去到5秒以上，包括gc延迟，不过一般也不会返回如此巨大的数据量
 *
 * @author zhijie
 */
@Slf4j
public class DictLoader {

    /**
     * 数据提供接口
     */
    private IDictDataCollection dataCollection;

    public DictLoader(IDictDataCollection dataCollection) {
        this.dataCollection = dataCollection;
    }

    public void load(Object loadTarget) {
        TimeInterval timer = DateUtil.timer();
        load(0, loadTarget);
        log.debug("字典注入耗时：" + timer.interval() + " ms");
    }

    private void load(int depth, Object loadTarget) {
        if (loadTarget == null || dataCollection == null) {
            return;
        }
        Object injectObject;
        if (loadTarget instanceof PageInfoModel) {
            injectObject = ((PageInfoModel) loadTarget).getDatas();
        } else {
            injectObject = loadTarget;
        }
        if (injectObject == null) {
            return;
        }
        if (injectObject instanceof Map) {
            ((Map) injectObject).forEach((key, value) -> fillDictionary(depth, value));
        } else if (injectObject instanceof List) {
            ((List) injectObject).forEach(item -> fillDictionary(depth, item));
        } else {
            fillDictionary(depth, injectObject);
        }
    }

    private void fillDictionary(int depth, Object model) {
        if (model == null) {
            return;
        }
        if (depth > 2) {
            // 不再继续往下遍历
            return;
        }
        if (model.getClass().getAnnotation(InjectDict.class) == null) {
            return;
        }
        Field[] fields = model.getClass().getDeclaredFields();
        for (Field field : fields) {
            DictName dictName = field.getAnnotation(DictName.class);
            if (dictName == null) {
                if (ignoreType(field)) {
                    continue;
                }
                load(depth + 1, getFieldValue(field, model));
                continue;
            }
            // 获取关联的值
            String dictValue = getValue(model, dictName.joinField());
            if (StringUtils.isBlank(dictValue)) {
                continue;
            }
            String displayName;
            if (dictValue.contains(",")) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String item : dictValue.split(",")) {
                    item = item.trim();
                    if( StringUtils.isBlank(item) ){
                        continue;
                    }
                    String itemDisplayName = dataCollection.dictMapping(dictName.code(), item);
                    if (StringUtils.isBlank(itemDisplayName)) {
                        continue;
                    }
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append(itemDisplayName);
                }
                displayName = stringBuilder.toString();
            } else {
                displayName = dataCollection.dictMapping(dictName.code(), dictValue);
            }
            if (StringUtils.isBlank(displayName)) {
                continue;
            }
            // 设置字典项
            setValue(model, field, displayName);
        }
    }

    private Object getFieldValue(Field field, Object target) {
        try {
            Object value;
            if (field.isAccessible()) {
                value = field.get(target);
            } else {
                field.setAccessible(true);
                value = field.get(target);
                field.setAccessible(false);
            }
            return value;
        } catch (IllegalAccessException e) {
            // nothing to do.
            e.printStackTrace();
        }
        return null;
    }

    private static final String TYPE_JAVA_LANG = "java.lang";
    private static final String TYPE_INT = "int";
    private static final String TYPE_LONG = "long";
    private static final String TYPE_FLOAT = "float";
    private static final String TYPE_DOUBLE = "double";
    private static final String TYPE_BOOLEAN = "boolean";

    private boolean ignoreType(Field field) {
        String type = field.getType().getName();
        if (type.startsWith(TYPE_JAVA_LANG)) {
            return true;
        }
        if (TYPE_INT.equals(type) || TYPE_LONG.equalsIgnoreCase(type) || TYPE_FLOAT.equals(type) ||
                TYPE_DOUBLE.equals(type) || TYPE_BOOLEAN.equalsIgnoreCase(type)) {
            return true;
        }
        return false;
    }

    private void setValue(Object bean, Field field, String value) {
        if (value == null) {
            return;
        }
        try {
            if (field.isAccessible()) {
                field.set(bean, value);
            } else {
                field.setAccessible(true);
                field.set(bean, value);
                field.setAccessible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getValue(Object bean, String fieldName) {
        try {
            Field target = bean.getClass().getDeclaredField(fieldName);
            String value;
            if (target.isAccessible()) {
                value = String.valueOf(target.get(bean));
            } else {
                target.setAccessible(true);
                value = String.valueOf(target.get(bean));
                target.setAccessible(false);
            }
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字典数据获取
     */
    public interface IDictDataCollection {

        /**
         * 根据code获取字典项,实现类应该对该接口做缓存
         *
         * @param code
         * @param value
         * @return
         */
        String dictMapping(String code, String value);

    }

}
