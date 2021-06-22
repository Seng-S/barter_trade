package com.supinfo.filter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bargenson
 */
@WebFilter(urlPatterns="/admin/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        if(httpRequest.getSession().getAttribute("user") == null) {
            httpResponse.setStatus(401);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();        
            out.print("{\"authorized\": false}");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Do nothing
    }

}
