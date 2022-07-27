package com.itheima.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.itheima.reggie.comon.BaseContext;
import com.itheima.reggie.comon.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author HuJiangTao
 * @create 2022/7/26 22:17
 */
@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestUrl = request.getRequestURI();
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/employee/page",
                "/backend/**",
                "/front/**"
        };
        //log.info("拦截到请求：{}",request.getRequestURI());

        boolean checkUrl = check(urls,requestUrl);

        if (checkUrl){
            //log.info("本次请求{}不需要登录");
            filterChain.doFilter(request, response);
            return;
        }
        if(request.getSession().getAttribute("employee")!=null){
            //log.info("用户已登录，用户id为:{}",request.getSession().getAttribute("employee"));
            Long eId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(eId);
            filterChain.doFilter(request, response);
            return;
        }
       // log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;




//        log.info("拦截到请求：{}",request.getRequestURI());
//        filterChain.doFilter(request, response);
    }


    /**
       路径匹配
     */
    public boolean check(String[] urls,String requestURI){
        for (String url: urls) {
            boolean match = PATH_MATCHER.match(url,requestURI);
            if (match){
                return true;
            }
        }
        return false;
    }
}
