package ua.kpi.controller.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.kpi.controller.TextConstants.*;

@WebFilter("/*")
public class SessionLocaleFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(SessionLocaleFilter.class);

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String language;

        if (request.getParameter(LANG) != null) {
            language = request.getParameter(LANG);

        } else if (session.getAttribute(SESSION_LANGUAGE) != null) {
            language = (String) session.getAttribute(SESSION_LANGUAGE);

        } else {
            language = UK;
        }

        session.setAttribute(SESSION_LANGUAGE, language);

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
