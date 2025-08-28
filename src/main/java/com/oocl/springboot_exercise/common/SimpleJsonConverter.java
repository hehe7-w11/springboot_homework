package com.oocl.springboot_exercise.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SimpleJsonConverter {

    /**
     * 将实体对象转为JSON字符串
     */
    public static String toJson(Object obj) {
        if (obj == null) {
            return "null";
        }

        // 处理基本类型（这里简化处理，实际可扩展更多类型）
        if (obj instanceof String) {
            return "\"" + escape((String) obj) + "\"";
        }
        if (obj instanceof Number || obj instanceof Boolean) {
            return obj.toString();
        }

        // 处理实体类（通过反射获取字段）
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields(); // 获取所有声明的字段（包括private）
        List<String> fieldEntries = new ArrayList<>();

        for (Field field : fields) {
            field.setAccessible(true); // 允许访问private字段
            String fieldName = field.getName();
            Object fieldValue;
            try {
                fieldValue = field.get(obj); // 获取字段值
            } catch (IllegalAccessException e) {
                continue; // 忽略无法访问的字段
            }

            // 递归转换字段值为JSON
            String jsonValue = toJson(fieldValue);
            fieldEntries.add("\"" + fieldName + "\":" + jsonValue);
        }

        // 拼接为JSON对象格式：{key:value, key:value}
        return "{" + String.join(",", fieldEntries) + "}";
    }

    /**
     * 转义JSON中的特殊字符（如双引号、换行符等）
     */
    private static String escape(String value) {
        if (value == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (char c : value.toCharArray()) {
            switch (c) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }
}