package cn.koboro.points.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xdw
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ResultEnum implements CodeEnum {

    UNKONW_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    ;

    private Integer code;

    private String msg;

}
