package org.patentminer.util;

import org.patentminer.exception.CheckException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class CommonUtil {

    public static Object unboxOptional(Optional optional) {
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    public static Map<String, Object> getParameterMap(HttpServletRequest request) {
        Map<String, String[]> properties = request.getParameterMap();
        Map<String, Object> map = new HashMap<String, Object>();
        Set<String> keySet = properties.keySet();
        for (String key : keySet) {
            String[] values = properties.get(key);
            String value = values[0];
            map.put(key, value);
        }
        return map;
    }

    public static String getHeader(HttpServletRequest request, String key) {
        String val = request.getHeader(key);
        if (val == null) {
            throw new CheckException("header not exists");
        } else {
            return val;
        }
    }
}
