package com.shadow.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.reader.UnicodeReader;

import java.util.*;

/**
 * @author shadow
 * @create 2021-02-12
 * @description
 */
public class YamlUtils {

    private static Resource[] ymlResources = {new ClassPathResource("classpath:application.yml")};
    private static Map<String, Object> map = new HashMap<>(200);

    public static Map<String, Object> ymlHandler(Resource[] ymlResources) throws Exception {
        // 返回结果
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        Yaml yaml = new Yaml();
        // 多个文件处理
        Iterator<Resource> iterator = Arrays.stream(ymlResources).iterator();
        while (iterator.hasNext()) {
            Resource resource = iterator.next();
            UnicodeReader reader = new UnicodeReader(resource.getInputStream());
            Object load = yaml.load(reader);
            // 这里只是简单处理，需要多个方式可以自己添加
            if (load instanceof Map) {
                Map map = (Map) load;
                buildFlattenedMap(result, map, null);
            }
            reader.close();
        }
        return result;
    }

    private static void buildFlattenedMap(Map<String, Object> result, Map<String, Object> source, @Nullable String path) {
        // 循环读取源数据
        source.forEach((k, v) -> {
            // 如果存在路径进行拼接
            if (StringUtils.hasText(path)) {
                if (k.startsWith("[")) {
                    k = path + k;
                } else {
                    k = path + "." + k;
                }
            }
            // 数据类型匹配
            if (v instanceof String) {
                result.put(k, v);
            } else if (v instanceof Map) {
                // 如果是map，继续读取
                Map<String, Object> map = (Map) v;
                buildFlattenedMap(result, map, k);
            } else if (v instanceof Collection) {
                Collection<Object> collection = (Collection) v;
                if (collection.isEmpty()) {
                    result.put(k, "");
                } else {
                    int count = 0;
                    Iterator<Object> iterator = collection.iterator();
                    while (iterator.hasNext()) {
                        Object next = iterator.next();
                        buildFlattenedMap(result, Collections.singletonMap("[" + count++ + "]", next), k);
                    }
                }
            } else {
                result.put(k, v != null ? v : "");
            }
        });
    }


    public static String getString(String key, Resource[] ymlResources) {
        // 每次重新加载
        try {
            if(ymlResources == null || ymlResources.length == 0) {
                ymlResources = YamlUtils.ymlResources;
            }
            map = YamlUtils.ymlHandler(ymlResources);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return handleResult(key);
    }

    private static String handleResult(String key) {
        if(map.keySet().contains(key)) {
            return String.valueOf(map.get(key));
        }
        return null;
    }

    public static String getStringDefaultValue(String key, String defaultValue, Resource[] ymlResources) {
        // 如果map中有key就使用map中的
        if(map.get(key) != null) {
            return (String) map.get(key);
        } else {
            // 没有就重新加载
            try {
                if(ymlResources == null || ymlResources.length == 0) {
                    ymlResources = YamlUtils.ymlResources;
                }
                map = YamlUtils.ymlHandler(ymlResources);
                // key 不存在就使用默认的
                map.putIfAbsent(key, defaultValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return handleResult(key);
        }
    }


}
