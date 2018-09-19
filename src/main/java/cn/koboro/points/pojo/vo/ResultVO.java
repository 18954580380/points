package cn.koboro.points.pojo.vo;

import cn.koboro.points.enums.ResultEnum;
import lombok.Data;

/**
 * http请求返回的最外层对象
 *
 * @param <T> 返回对象的泛型
 * @author xdw
 */
@Data
public class ResultVO<T> {

    /**
     * 错误码.
     */
    private Integer code;

    /**
     * 提示信息.
     */
    private String msg;

    /**
     * 具体的内容.
     */
    private T data;


    public void setResultEnums(ResultEnum resultEnum, String... args) {
        this.setCode(resultEnum.getCode());
        if (args.length > 0) {
            this.setMsg(String.format(resultEnum.getMsg(), args));
        } else {
            this.setMsg(resultEnum.getMsg());
        }
    }
    
    public static ResultVO success(Object object) {
        ResultVO result = new ResultVO();
        result.setCode(1);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO result = new ResultVO();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
