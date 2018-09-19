package cn.koboro.points.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 解决Rest接口调用过程中，跨域访问问题
 *
 * @author xdw
 * @version 1.0
 */
@Order(1)
//重点
@WebFilter(filterName = "CORSFilter", urlPatterns = "/*")
public class CORSFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;


        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");


        httpServletResponse
                .setHeader(
                        "Access-Control-Allow-Headers",
                        "User-Agent,Origin,Cache-Control,Content-type,Date,Server,withCredentials,AccessToken");


        httpServletResponse.setHeader("Access-Control-Allow-Credentials",
                "true");


        httpServletResponse.setHeader("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");


        httpServletResponse.setHeader("Access-Control-Max-Age", "1209600");


        httpServletResponse.setHeader("Access-Control-Expose-Headers",
                "accesstoken");


        httpServletResponse.setHeader("Access-Control-Request-Headers",
                "accesstoken");


        httpServletResponse.setHeader("Expires", "-1");


        httpServletResponse.setHeader("Cache-Control", "no-cache");


        httpServletResponse.setHeader("pragma", "no-cache");


        chain.doFilter(request, response);


    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {


    }

    @Override
    public void destroy() {
    }


}