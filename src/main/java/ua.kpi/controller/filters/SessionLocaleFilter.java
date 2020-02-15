package ua.kpi.controller.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class SessionLocaleFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(SessionLocaleFilter.class);

    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String language;

        if (request.getParameter("lang") != null) {
            language = request.getParameter("lang");

        } else if (session.getAttribute("sessionLang") != null) {
            language = (String) session.getAttribute("sessionLang");

        } else {
            language = "uk";
        }

        session.setAttribute("sessionLang", language);

        filterChain.doFilter(request, response);
    }

    public void destroy() {
    }
}
