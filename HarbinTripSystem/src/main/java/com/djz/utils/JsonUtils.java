package com.djz.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * @author djz
 * @date 2021/4/2 -6:34
 */
public class JsonUtils {

    public static Map<String, Object> parseJSON2Map(String jsonStr) {
       // 最外层解析
        if (jsonStr != null && jsonStr.startsWith("{") && jsonStr.endsWith("}")) {
            Map<String, Object> map = new HashMap<String, Object>();

            JSONObject json = JSONObject.parseObject(jsonStr);
            for (Object k : json.keySet()) {

                Object v = json.get(k);

                // 字段值为null直接转为空
                if (null == v) {
                    map.put(k.toString(), "");
                } // 如果内层还是数组的话，继续解析
                else if (v instanceof JSONArray) {
                    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                    Iterator<Object> it = ((JSONArray) v).iterator();
                    while (it.hasNext()) {
                        JSONObject json2 = (JSONObject) it.next();
                        list.add(parseJSON2Map(json2.toString()));
                    }
                    map.put(k.toString(), list);
                } else {
                    Map<String, Object> m = parseJSON2Map(v.toString());
                    if (m == null) {
                        map.put(k.toString(), v);
                    } else {
                        map.put(k.toString(), m);
                    }
                }
            }
            return map;
        } else {
            return null;
        }
    }
}
