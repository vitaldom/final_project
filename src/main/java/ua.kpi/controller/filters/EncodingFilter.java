package ua.kpi.controller.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements Filter {

    private static final String TEXT_HTML = "text/html";
    private static final String UTF_8 = "UTF-8";

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        response.setContentType(TEXT_HTML);
        response.setCharacterEncoding(UTF_8);
        request.setCharacterEncoding(UTF_8);

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}