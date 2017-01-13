package com.moze.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Moze
 * Date: 2017-01-09
 * Time: 19:57
 */
@WebFilter(filterName = "EncodingFilter",urlPatterns = "*",
    initParams = @WebInitParam(name="encoder",value = "utf-8"))

public class EncodingFilter implements Filter {
    private String encoder="";
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
       req.setCharacterEncoding(encoder);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        encoder=config.getInitParameter("encoder");
    }

}
