package cn.koboro.points.core;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Condition;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author xdw
 */
public interface IBaseMapper<T> extends Mapper<T> {

    default Condition getCondition() {
        return new Condition(getGenericClass());
    }

    default Class getGenericClass() {
        Class clazz = null;
        Class c = this.getClass();
        Type[] types = c.getGenericInterfaces();
        for (Type type : types) {
            if (type instanceof Class && IBaseMapper.class.isAssignableFrom((Class) type)) {
                Type[] params = ((Class) type).getGenericInterfaces();
                for (Type param : params) {
                    if (param instanceof ParameterizedType) {
                        Type[] genericClasses = ((ParameterizedType) param).getActualTypeArguments();
                        if (genericClasses.length > 0) {
                            clazz = (Class<T>) genericClasses[0];
                        }
                    }
                }
            }
        }
        return clazz;

    }

}
