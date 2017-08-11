package com.ty.warwolf.util;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ 文件名:   StringUtil
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午2:40
 * @ 描述:
 */

public class StringUtil {
    protected static final String TAG = StringUtil.class.getSimpleName();

    public static String formatDoubleTwo(double d) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        String format = nf.format(d);
        if (format.startsWith(".")) {
            format = "0" + format;
        }
        return format;
    }

    /**
     * 增加空白
     */
    public static String addBlank(int size) {
        return String.format("%" + size + "s", "");
    }

    /**
     * 判断字符串是否为null或者""
     */
    public static boolean isEmptyOrNull(String content) {
        if (content == null || content.equals("")) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否为IP地址
     */
    public static boolean isIPAddress(String ipString) {
        if (ipString != null) {
            String[] singleArray = ipString.split("\\.");
            if (singleArray == null) {
                return false;
            }
            for (String numString : singleArray) {
                if (isEmptyOrNull(numString.trim())) {
                    return false;
                }
                try {
                    int num = Integer.parseInt(numString.trim());
                    if (num < 0 || num > 255) {
                        return false;
                    }
                } catch (NumberFormatException e) {
                    return false;
                }

            }
            return true;
        }
        return false;
    }

    /**
     * 是否是email地址
     */
    public static boolean isEmailAddress(String emailString) {
        String format = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
        return isMatch(format, emailString);
    }

    /**
     * 是否为数字
     */
    public static boolean isDigit(String digitString) {
        if (!isEmptyOrNull(digitString)) {
            String regex = "[0-9]*";
            return isMatch(regex, digitString);
        }
        return false;
    }

    /**
     * 字符串正则校验
     */
    public static boolean isMatch(String regex, String string) {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    /**
     * 是否为URL地址
     */
    public static boolean isUrl(String strIp) {
        String strPattern = "^((https?)|(ftp))://(?:(\\s+?)(?::(\\s+?))?@)?([a-zA-Z0-9\\-.]+)"
                + "(?::(\\d+))?((?:/[a-zA-Z0-9\\-._?,'+\\&%$=~*!():@\\\\]*)+)?$";
        return isMatch(strPattern, strIp);
    }

    /**
     * String 转换成Unicode
     *
     * @param string 传入汉字
     * @return
     */
    public static String string2Unicode(String string) {
        if (!isEmptyOrNull(string)) {
            char[] charArray = string.toCharArray();
            StringBuffer buffer = new StringBuffer();
            for (char ch : charArray) {
                int code = (int) ch;
                buffer.append(code);
            }
            return buffer.toString();
        }
        return null;
    }

    /**
     * Unicode转换成String
     *
     * @param string
     * @return
     */
    public static String unicode2String(String string) {
        if (!isEmptyOrNull(string)) {
            int end = 0;
            String noSpace = string.trim();
            int count = noSpace.length() / 5;
            StringBuffer buffer = new StringBuffer();

            for (int j = 0; j < count; j++) {
                end += 5;
                int uCode = Integer.valueOf(noSpace.substring(j * 5, end));
                buffer.append((char) uCode);

            }
            return buffer.toString();
        }
        return null;
    }


    /**
     * 获取url参数
     */
    public static String getParamValueOfUrl(String url, String paramName) {
        try {
            String urls[] = url.split("[?]");
            if (urls.length > 1) {
                String param = urls[1];
                String params[] = param.split("[&]");
                for (String string : params) {
                    String keyAndValue[] = string.split("[=]");
                    if (keyAndValue.length > 1) {
                        String key = keyAndValue[0];
                        String value = keyAndValue[1];
                        if (key.equalsIgnoreCase(paramName)) {
                            return value;
                        }
                    }
                }
            }
        } catch (Exception e) {
            return "";
        }
        return "";
    }

    /**
     * 半角转换为全角
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * 去除特殊字符或将所有中文标号替换为英文标号
     *
     * @param str
     * @return
     */
    public static String stringFilter(String str) {
        str = str.replaceAll("【", "[").replaceAll("】", "]").replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号
        String regEx = "[『』]"; // 清除掉特殊字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 根据Unicode编码完美的判断中文汉字和符号
     *
     * @param c
     * @return
     */

    private static boolean isChinese(char c) {

        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);

        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS

                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS

                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A

                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B

                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION

                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS

                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {

            return true;

        }

        return false;

    }

    /**
     * 完整的判断中文汉字和符号
     *
     * @param strName
     * @return
     */

    public static boolean isChinese(String strName) {

        if (strName == null) {
            return false;
        } else {
            char[] ch = strName.toCharArray();
            for (int i = 0; i < ch.length; i++) {
                char c = ch[i];
                if (isChinese(c)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static String encodeChinese(String string) {
        if (string == null) {
            return null;
        } else {
            if (isChinese(string)) {
                try {
                    string = URLEncoder.encode(string, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    string = URLEncoder.encode(string);
                }
                return string;
            } else {
                return string;
            }
        }
    }


    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    public static String formatString(String string) {
        String notice = "";
        try {
            notice = URLEncoder.encode(string, "utf-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return notice;
    }

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return "0K";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "K";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "M";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(String size) {
        if (size == null || TextUtils.isEmpty(size.trim())) {
            return "0K";
        }

        double kiloByte = Double.valueOf(size) / 1024;
        if (kiloByte < 1) {
            return "0K";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "K";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "M";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }
}
