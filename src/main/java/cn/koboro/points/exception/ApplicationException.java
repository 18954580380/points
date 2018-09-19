package cn.koboro.points.exception;

import cn.koboro.points.enums.ResultEnum;

/**
 * 自定义运行时异常
 *
 * @author xdw
 */
public class ApplicationException extends RuntimeException {

    private Integer code;

    public ApplicationException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public ApplicationException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
