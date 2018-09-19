package cn.koboro.points.utils;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static String StringPoolNULL = "null";
    private static String StringPoolBLANK = "";

    public static boolean equals(String s1, String s2) {
        if ((s1 == null) && (s2 == null)) {
            return true;
        } else if ((s1 == null) || (s2 == null)) {
            return false;
        } else {
            return s1.equals(s2);
        }
    }

    public static boolean isChar(char c) {
        return Character.isLetter(c);
    }

    public static boolean isChar(String s) {
        if (isNull(s)) {
            return false;
        }

        char[] c = s.toCharArray();

        for (int i = 0; i < c.length; i++) {
            if (!isChar(c[i])) {
                return false;
            }
        }

        return true;
    }

    public static boolean isDigit(char c) {
        int x = c;

        if ((x >= 48) && (x <= 57)) {
            return true;
        }

        return false;
    }

    public static boolean isDigit(String s) {
        if (isNull(s)) {
            return false;
        }

        char[] c = s.toCharArray();

        for (int i = 0; i < c.length; i++) {
            if (!isDigit(c[i])) {
                return false;
            }
        }

        return true;
    }

    public static boolean isHex(String s) {
        if (isNull(s)) {
            return false;
        }

        return true;
    }

    public static boolean isHTML(String s) {
        if (isNull(s)) {
            return false;
        }

        if (((s.indexOf("<html>") != -1) || (s.indexOf("<HTML>") != -1))
                && ((s.indexOf("</html>") != -1) || (s.indexOf("</HTML>") != -1))) {

            return true;
        }

        return false;
    }


    public static boolean isDate(int month, int day, int year) {
        return isGregorianDate(month, day, year);
    }

    public static boolean isGregorianDate(int month, int day, int year) {
        if ((month < 0) || (month > 11)) {
            return false;
        }

        int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (month == 1) {
            int febMax = 28;

            if (((year % 4) == 0) && ((year % 100) != 0) || ((year % 400) == 0)) {

                febMax = 29;
            }

            if ((day < 1) || (day > febMax)) {
                return false;
            }
        } else if ((day < 1) || (day > months[month])) {
            return false;
        }

        return true;
    }

    public static boolean isJulianDate(int month, int day, int year) {
        if ((month < 0) || (month > 11)) {
            return false;
        }

        int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (month == 1) {
            int febMax = 28;

            if ((year % 4) == 0) {
                febMax = 29;
            }

            if ((day < 1) || (day > febMax)) {
                return false;
            }
        } else if ((day < 1) || (day > months[month])) {
            return false;
        }

        return true;
    }

    public static boolean isEmailAddressSpecialChar(char c) {

        // LEP-1445

        for (int i = 0; i < _EMAIL_ADDRESS_SPECIAL_CHAR.length; i++) {
            if (c == _EMAIL_ADDRESS_SPECIAL_CHAR[i]) {
                return true;
            }
        }

        return false;
    }

    public static boolean isName(String name) {
        if (isNull(name)) {
            return false;
        }

        char[] c = name.trim().toCharArray();

        for (int i = 0; i < c.length; i++) {
            if (((!isChar(c[i])) && (!Character.isWhitespace(c[i])))
                    || (c[i] == ',')) {

                return false;
            }
        }

        return true;
    }

    public static boolean isNumber(String number) {
        if (isNull(number)) {
            return false;
        }

        char[] c = number.toCharArray();

        for (int i = 0; i < c.length; i++) {
            if (!isDigit(c[i])) {
                return false;
            }
        }

        return true;
    }

    public static boolean isNull(Object obj) {
        if (obj instanceof Long) {
            return isNull((Long) obj);
        } else if (obj instanceof String) {
            return isNull((String) obj);
        } else if (obj == null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNull(Long l) {
        if ((l == null) || l.longValue() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNull(String s) {
        if (s == null) {
            return true;
        }

        s = s.trim();

        if (s == null || (s.equals(StringPoolNULL)) || (s.equals(StringPoolBLANK))) {
            return true;
        }

        return false;
    }

    public static boolean isNull(Object[] array) {
        if ((array == null) || (array.length == 0)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    public static boolean isNotNull(Long l) {
        return !isNull(l);
    }

    public static boolean isNotNull(String s) {
        return !isNull(s);
    }

    public static boolean isNotNull(Object[] array) {
        return !isNull(array);
    }

    public static boolean isPassword(String password) {
        if (isNull(password)) {
            return false;
        }

        if (password.length() < 4) {
            return false;
        }

        char[] c = password.toCharArray();

        for (int i = 0; i < c.length; i++) {
            if ((!isChar(c[i])) && (!isDigit(c[i]))) {

                return false;
            }
        }

        return true;
    }

    public static boolean isVariableTerm(String s) {
        if (s.startsWith("[$") && s.endsWith("$]")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isMail(String mail) {
        String mailregex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return match(mailregex, mail);
    }

    public static boolean match(String regexstr, String str) {
        Pattern regex = Pattern.compile(regexstr, Pattern.CASE_INSENSITIVE
                | Pattern.DOTALL);
        Matcher matcher = regex.matcher(str);
        return matcher.matches();
    }

    private static final Pattern P_MOBILE = Pattern.compile("^(13|15|18|14|17)[0-9]{9}$");

    //    private static final Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
    public static boolean isMobile(String mobiles) {
        Matcher m = P_MOBILE.matcher(mobiles);
        return m.matches();
    }

    private static final Pattern P_TEL = Pattern.compile("^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{1,5}))?$");

    public static boolean isTel(String tel) {
        Matcher m = P_TEL.matcher(tel);
        return m.matches();
    }

    private static final Pattern P_USERNAME = Pattern.compile("^[a-z_][a-z0-9_]{4,32}$");

    public static boolean isUsername(final String username) {
        Matcher matcher = P_USERNAME.matcher(username);
        return matcher.matches();
    }

    public static boolean Match(String regexstr, String str) {
        Pattern regex = Pattern.compile(regexstr, Pattern.CASE_INSENSITIVE
                | Pattern.DOTALL);
        Matcher matcher = regex.matcher(str);
        return matcher.matches();
    }

    private static final Pattern P_QQ = Pattern.compile("^[1-9]*[1-9][0-9]*$");

    public static boolean isQQ(String qq) {
        Matcher m = P_QQ.matcher(qq);
        return m.matches();
    }

    private static char[] _EMAIL_ADDRESS_SPECIAL_CHAR = new char[]{'.', '!',
            '#', '$', '%', '&', '\'', '*', '+', '-', '/', '=', '?', '^', '_',
            '`', '{', '|', '}', '~'};

    /**
     * @return true对象为空 false不为空
     * @author HSL
     * @desc:校验对象是否为空
     * @date:2017:13:18
     */
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        } else if (o instanceof String) {
            return ((String) o).length() == 0;
        } else if (o instanceof Collection) {
            return ((Collection) o).size() == 0;
        } else if (o instanceof Map) {
            return ((Map) o).size() == 0;
        } else if (o instanceof Object[]) {
            return ((Object[]) o).length == 0;
        } else {
            return false;
        }
    }

    public static boolean haveCreatedTime(Date s) {
        System.out.println(s);
//        if (StringUtils.isNotBlank(s)) {
//            return true;
//        }
        if (s != null) {
            return true;
        }
        return false;
    }
}
