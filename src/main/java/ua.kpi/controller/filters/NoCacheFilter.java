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

    @Override
    public void doFilter(ServletRequest request, ServletResponse resp, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) resp;
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Expires", "0");

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}