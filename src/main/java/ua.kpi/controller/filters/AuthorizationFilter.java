package ua.kpi.controller.filters;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    Logger LOGGER = LogManager.getLogger(AuthorizationFilter.class);

    @Override
    public void init(FilterConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        Set<String> loggedUsers = new HashSet<>();
        context.setAttribute("loggedUsers", loggedUsers);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //TODO add body
        String requestedUri = request.getRequestURI();
//        LOGGER.debug("Requested URI in Authorization filter: {}", requestedUri); //TODO



        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
