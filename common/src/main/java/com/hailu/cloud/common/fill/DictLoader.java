package com.hailu.cloud.common.fill;

import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 字典项自动填充，仅对第一层级做处理，如果有子列表不做填充，后面有需要再处理
 *
 * @author zhijie
 */
public class DictLoader {

    /**
     * 数据提供接口
     */
    private IDictDataCollection dataCollection;

    public DictLoader(IDictDataCollection dataCollection) {
        this.dataCollection = dataCollection;
    }

    public void load(Object loadTarget) {
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
            ((Map) injectObject).forEach((key, value) -> fillDictionary(value));
        } else if (injectObject instanceof List) {
            ((List) injectObject).forEach(item -> fillDictionary(item));
        } else {
            fillDictionary(injectObject);
        }
    }

    private void fillDictionary(Object model) {
        if (model.getClass().getAnnotation(InjectDict.class) == null) {
            return;
        }
        Field[] fields = model.getClass().getDeclaredFields();
        for (Field field : fields) {
            DictName dictName = field.getAnnotation(DictName.class);
            if (dictName == null) {
                continue;
            }
            // 获取关联的值
            String dictValue = getValue(model, dictName.joinField());
            if (StringUtils.isBlank(dictValue)) {
                continue;
            }
            String displayName = dataCollection.dictMapping(dictName.code(), dictValue);
            if (StringUtils.isBlank(displayName)) {
                continue;
            }
            // 设置字典项
            setValue(model, field, displayName);
        }
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
