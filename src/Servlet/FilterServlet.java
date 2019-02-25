package Servlet;

import javax.servlet.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class FilterServlet implements Filter {
    private String encode;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encode);
//        request.setCharacterEncoding(config.getInitParameter("charset"));
//        HttpServletRequest hrequest = (HttpServletRequest)request;
//        HttpServletResponse hresponse = (HttpServletResponse)response;
//        String LoginUser = (String)hrequest.getSession().getAttribute("LoginUser");
//        //告诉服务器已经处理完毕
        chain.doFilter(request, response);
    }
    @Override
    public void init(FilterConfig config) throws ServletException {
        encode=config.getInitParameter("encode");
    }


}
