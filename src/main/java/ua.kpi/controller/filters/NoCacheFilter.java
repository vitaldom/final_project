package ua.kpi.controller.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class NoCacheFilter implements Filter {

    Logger LOGGER = LogManager.getLogger(NoCacheFilter.class);

    private static final String CACHE_CONTROL = "Cache-Control";
    private static final String NO_CACHE = "no-cache, no-store, must-revalidate";
    private static final String EXPIRES = "Expires";
    private static final String ZERO = "0";

    @Override
    public void doFilter(ServletRequest request, ServletResponse resp, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) resp;
        response.setHeader(CACHE_CONTROL, NO_CACHE);
        response.setHeader(EXPIRES, ZERO);

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}