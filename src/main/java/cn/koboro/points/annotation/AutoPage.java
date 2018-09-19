package cn.koboro.points.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自动分页
 * @author xdw
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoPage {

    String pageNum() default "pageNum";
    String pageSize() default "pageSize";

}
