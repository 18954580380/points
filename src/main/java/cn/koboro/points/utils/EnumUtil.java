package cn.koboro.points.utils;

import cn.koboro.points.enums.CodeEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xdw
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        Stream<T> stream = Arrays.stream(enumClass.getEnumConstants());

        List<T> list = stream.filter(c -> code.equals(c.getCode()))
                .collect(Collectors.toList());

        return list.size() > 0 ? list.get(0) : null;
    }

}
