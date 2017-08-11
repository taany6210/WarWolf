package com.ty.warwolf.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @ 文件名:   JsonHelper
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午2:38
 * @ 描述:
 */

public class JsonHelper {
    /**
     * 将Javabean转换为Map
     *
     * @param javaBean javaBean
     * @return Map对象
     */
    public static Map<String, Object> toMap(Object javaBean) {
        Map<String, Object> result = new HashMap<String, Object>();
        Method[] methods = javaBean.getClass().getDeclaredMethods();
        for (Method method : methods) {
            try {
                if (method.getName().startsWith("get")) {
                    String field = method.getName();
                    field = field.substring(field.indexOf("get") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);
                    Object value = method.invoke(javaBean, (Object[]) null);
                    result.put(field, null == value ? "" : value.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 将Json对象转换成Map
     *
     * @param jsonString
     * @return Map对象
     * @throws JSONException
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        Map<String, Object> result = new HashMap<String, Object>();
        Iterator<String> iterator = jsonObject.keys();
        String key = null;
        String value = null;
        while (iterator.hasNext()) {
            key = iterator.next();
            value = jsonObject.getString(key);
            result.put(key, value);
        }
        return result;

    }

    /**
     * 将JavaBean转换成JSONObject（通过Map中转）
     *
     * @param bean javaBean
     * @return json对象
     */
    public static JSONObject toJSON(Object bean) {
        return new JSONObject(toMap(bean));
    }

    /**
     * 将Map转换成Javabean
     *
     * @param javabean javaBean
     * @param data     Map数据
     */
    public static Object toJavaBean(Object javabean, Map<String, Object> data) {
        Method[] methods = javabean.getClass().getDeclaredMethods();
        for (Method method : methods) {
            try {
                if (method.getName().startsWith("set")) {
                    String field = method.getName();
                    field = field.substring(field.indexOf("set") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);
                    method.invoke(javabean, new Object[]{data.get(field)});
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return javabean;
    }

    /**
     * JSONObject到JavaBean
     *
     * @param javabean
     * @param jsonString
     * @throws ParseException json解析异常
     * @throws JSONException
     */
    public static void toJavaBean(Object javabean, String jsonString)
            throws ParseException, JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        Map<String, Object> map = toMap(jsonObject.toString());
        toJavaBean(javabean, map);
    }

    /**
     * 根据json字符串生成java类
     *
     * @param packName   包名
     * @param className  类名
     * @param jsonString json字符串
     *                   如:"data":{
     *                   "deviceId":"796c57ccac7f4f68a9b0225f951dc739512744664",
     *                   "deviceSerial":"512744664",
     *                   "cameraId":"b86d66dc81e046d7a85c8b244d48f017",
     *                   "cameraNo":1,
     *                   "cameraName":"DS-2CD3Q10FD-IW(512744664)",
     *                   "status":0,
     *                   "isShared":"0",
     *                   "picUrl":"https://i.ys7.com/assets/imgs/public/companyDevice.jpeg",
     *                   "isEncrypt":0
     *                   }
     */
    public static void generateJavaFile(String packName, String className,
                                        String jsonString) {
        try {
            File file = new File(className + ".java");
            if (file.exists())
                file.delete();
            file.createNewFile();
            PrintWriter writer = new PrintWriter(file);
            writer.write("package " + packName + ";\n\n");
            writer.write("public class " + className + " {\n\n");
            Map<String, Object> map = JsonHelper.toMap(jsonString);
            Set<String> set = map.keySet();
            String[] keys = new String[set.size()];
            set.toArray(keys);
            for (String key : keys) {
                writer.write("    private String " + key + ";\n");
            }
            writer.write("\n");
            for (String key : keys) {
                String methodKey = key.toUpperCase().charAt(0) + key.substring(1);
                writer.write("    public void set" + methodKey + "(String " + key + ") {\n        this." + key + " = " + key + ";\n}\n\n");
                writer.write("    public String get" + methodKey + "() {\n        return " + key + ";\n    }\n\n");
            }
            writer.write("}");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
