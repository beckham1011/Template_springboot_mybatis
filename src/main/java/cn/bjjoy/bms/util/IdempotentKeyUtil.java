package cn.bjjoy.bms.util;

import com.alibaba.fastjson.JSON;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class IdempotentKeyUtil {
    /**
     * 对接口的参数进行处理生成固定key
     *
     * @param method
     * @param args
     * @return
     */
    public static String generate(Method method, Object... args) {
        StringBuilder stringBuilder = new StringBuilder(method.toString());
        for (Object arg : args) {
            stringBuilder.append(toStrinhg(arg));
        }
        //进行md5等长加密
        return md5(stringBuilder.toString());
    }

    /**
     * 使用jsonObject对数据进行toString,(保持数据一致性)
     *
     * @param obj
     * @return
     */
    public static String toStrinhg(Object obj) {
        if (obj == null) {
            return "-";
        }
        return JSON.toJSONString(obj);
    }

    /**
     * 对数据进行MD5等长加密
     *
     * @param str
     * @return
     */
    public static String md5(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //选择MD5作为加密方式
            MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(str.getBytes());
            byte b[] = mDigest.digest();
            int j = 0;
            for (int i = 0, max = b.length; i < max; i++) {
                j = b[i];
                if (j < 0) {
                    i += 256;
                } else if (j < 16) {
                    stringBuilder.append(0);
                }
                stringBuilder.append(Integer.toHexString(j));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
