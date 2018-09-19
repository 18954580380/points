package cn.koboro.points.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xdw
 */
public class StringUtil {

    private StringUtil() {
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return (str != null) && (!"".equals(str));
    }

    private static class Constants {
        //序列字符池
//        public static final String ORDER_CHAR = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        public static final char[] ORDER_CHAR = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
                'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
                'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        //十六进制数字
        public static final char[] HEX_DIGITS = {
                '0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        //种子
        public static final int seed = 2017;
    }

    public final static String getMD5(String s) {

        char hexDigits[] = Constants.HEX_DIGITS;

        try {

            byte[] strTemp = s.getBytes();

            // 使用MD5创建MessageDigest对象

            MessageDigest mdTemp = MessageDigest.getInstance("MD5");

            mdTemp.update(strTemp);

            byte[] md = mdTemp.digest();

            int j = md.length;

            char str[] = new char[j * 2];

            int k = 0;

            for (int i = 0; i < j; i++) {

                byte b = md[i];

                str[k++] = hexDigits[b >> 4 & 0xf];

                str[k++] = hexDigits[b & 0xf];

            }

            return new String(str);

        } catch (Exception e) {

            return null;

        }

    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 根据时间戳从指定‘字符串范围内’获取唯一序列
     *
     * @param scope
     * @return
     */
    public static String getSequence(String scope) {
        scope = distinct(scope);

        //范围小于5采用默认取值范围， 不然返回值过长
        if (scope == null || scope.length() < 5) {
            scope = new String(Constants.ORDER_CHAR);
        }

        return baseString(System.currentTimeMillis(), scope);
    }

    /**
     * 十进制转 其他进制
     *
     * @param scope 范围
     * @return
     */
    public static String baseString(long num, String scope) {
        int base = scope.length();
        String str = "";
        if (num == 0) {
            return "";
        } else {
            str = baseString(num / base, scope);
            return str + scope.charAt((int) (num % base));
        }
    }

    /**
     * String 去重复
     *
     * @param str
     * @return
     */
    public static String distinct(String str) {

        if (str == null || "".equals(str)) {
            return str;
        }

        StringBuffer result = new StringBuffer();
        char[] cs = str.toCharArray();

        Arrays.stream(new char[][]{cs}).distinct().forEach(result::append);
        return result.toString();
    }

    /**
     * 生成位随机数
     *
     * @return
     */
    public static String getRandom(int length) {
        String random = String.valueOf(Math.random());
        int index = random.indexOf(".");
        String result = random.substring(index + 1, index + 1 + length);
        return result;
    }

    /**
     * 指定规则打乱
     *
     * @param str
     * @return
     */
    public static String disorganize(String str, Random rd) {
        if (str == null || "".equals(str)) {
            str = new String(Constants.ORDER_CHAR);
        }
        if (rd == null) {
            rd = new Random();
        }

        List<String> list = Arrays.asList(str.split(""));

        Collections.shuffle(list, rd);
        return list.stream().collect(Collectors.joining());
    }

    public static String disorganize(String str) {
        return disorganize(str, null);
    }

    public static String disorganize(Random rd) {
        return disorganize(null, rd);
    }

    public static String getShortUUID() {
        //获取uuid
        String[] aStr = UUID.randomUUID().toString().split("-");

        String scope = disorganize(new Random(Constants.seed));

        //固定种子,确保取值池是相同的,以确保最后取值绝对不会重复

        return Arrays.stream(aStr).map(e -> baseString(Long.parseLong(e, 16), scope))
                .collect(Collectors.joining());


    }

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String sign(Object object) throws JsonProcessingException {
        String serializeString = mapper.writeValueAsString(object);
        //加密
//		return EncryptRUtil.encryptCaseSensitive(serializeString);
        return serializeString;
    }

    public static <T> T parse(String param, TypeReference typeReference) throws JsonProcessingException {
        //解密
        try {
            //转对象
            return mapper.readValue(param, typeReference);
        } catch (IOException e) {
            throw JsonMappingException.fromUnexpectedIOE(e);
        }
    }

    public static String getUserDir() {
        return System.getProperty("user.dir");
    }
}
