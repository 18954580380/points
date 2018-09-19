package cn.koboro.points.configuration;

import cn.koboro.points.annotation.AutoPage;
import com.github.pagehelper.PageHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * @author xdw
 */
@Aspect
@Component
public class PageAspect {

    @Pointcut("execution(* cn.koboro.points.service.impl..*.*(..)) && @annotation(autoPage)")
    public void business(AutoPage autoPage) {
    }
    @Around("business(autoPage)")
    public Object around(ProceedingJoinPoint pjp, AutoPage autoPage) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String pageNum = request.getParameter(autoPage.pageNum());
        String pageSize = request.getParameter(autoPage.pageSize());
        if (isNum(pageNum) && isNum(pageSize)) {
            PageHelper.startPage(Integer.valueOf(pageNum), Integer.valueOf(pageSize));
        }
        return pjp.proceed(pjp.getArgs());
    }
    public static final Pattern IS_NUM = Pattern.compile("[0-9]+");
    public static boolean isNum(String str) {
        if (str == null)
            return false;
        if (str.length() == 0)
            return false;
        return IS_NUM.matcher(str).matches();
    }
}
