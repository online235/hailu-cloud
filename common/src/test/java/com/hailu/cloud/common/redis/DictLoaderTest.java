package com.hailu.cloud.common.redis;

import com.alibaba.fastjson.JSON;
import com.hailu.cloud.common.fill.DictLoader;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@DisplayName("字典填充测试")
public class DictLoaderTest {

    @DisplayName("测试单个对象")
    @Test()
    public void testSingleObject() {
        // 模拟数据库字典
        Map<String, Map<String, String>> cache = new HashMap() {{
            put("CATEGORY", new HashMap() {{
                put("1001", "分类1");
                put("1002", "分类2");
                put("1003", "分类3");
            }});
            put("STATUS", new HashMap() {{
                put("2001", "状态1");
                put("2002", "状态2");
                put("2003", "状态3");
            }});
        }};

        DictLoader loader = new DictLoader(code -> cache.get(code));

        PersonModel model = new PersonModel();
        model.setCategoryId("1001");
        log.info("原始：{}", JSON.toJSONString(model));
        loader.load(model);
        log.info("注入：{}", JSON.toJSONString(model));
        model.setCategoryId("1002");
        log.info("变更：{}", JSON.toJSONString(model));
        loader.load(model);
        log.info("注入：{}", JSON.toJSONString(model));

    }

    @DisplayName("测试List")
    @Test
    public void testList() {

        // 模拟数据库字典
        Map<String, Map<String, String>> cache = new HashMap() {{
            put("CATEGORY", new HashMap() {{
                put("1001", "分类1");
                put("1002", "分类2");
                put("1003", "分类3");
            }});
            put("STATUS", new HashMap() {{
                put("2001", "状态1");
                put("2002", "状态2");
                put("2003", "状态3");
            }});
        }};

        DictLoader loader = new DictLoader(code -> cache.get(code));
        List<PersonModel> datas = new ArrayList<>(10);
        PersonModel model = new PersonModel();
        model.setCategoryId("1001");
        datas.add(model);
        PersonModel model2 = new PersonModel();
        model2.setCategoryId("1002");
        datas.add(model2);

        log.info("原始：{}", JSON.toJSONString(datas));
        loader.load(datas);
        log.info("注入：{}", JSON.toJSONString(datas));

    }

    @DisplayName("测试Map")
    @Test
    public void testMap() {

        // 模拟数据库字典
        Map<String, Map<String, String>> cache = new HashMap() {{
            put("CATEGORY", new HashMap() {{
                put("1001", "分类1");
                put("1002", "分类2");
                put("1003", "分类3");
            }});
            put("STATUS", new HashMap() {{
                put("2001", "状态1");
                put("2002", "状态2");
                put("2003", "状态3");
            }});
        }};

        DictLoader loader = new DictLoader(code -> cache.get(code));
        Map<String, PersonModel> datas = new HashMap<>(10);
        PersonModel model = new PersonModel();
        model.setCategoryId("1001");
        datas.put(model.getCategoryId(), model);
        PersonModel model2 = new PersonModel();
        model2.setCategoryId("1002");
        datas.put(model2.getCategoryId(), model2);

        log.info("原始：{}", JSON.toJSONString(datas));
        loader.load(datas);
        log.info("注入：{}", JSON.toJSONString(datas));

    }

}
