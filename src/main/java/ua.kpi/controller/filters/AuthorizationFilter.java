package ua.kpi.controller.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.kpi.controller.inputcheck.InputChecker;
import ua.kpi.controller.path.ServletPath;
import ua.kpi.model.entities.AbstractAppUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static ua.kpi.controller.TextConstants.USER;

/**
 * Allows access to application resources to only registered users.
 * Start and registration pages are open for unregistered users.
 */
@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    Logger LOGGER = LogManager.getLogger(AuthorizationFilter.class);

    private static final Set<String> LOGGED_USERS = new HashSet<>();

    @Override
    public void init(FilterConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        context.setAttribute("loggedUsers", LOGGED_USERS);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String requestedUri = request.getRequestURI();

        if (requestedUri.equals(ServletPath.START_PAGE)
                || requestedUri.equals(ServletPath.REGISTRATION)
                || requestedUri.equals(ServletPath.LOGIN)) {
            filterChain.doFilter(request, response);

        } else if (checkAccessRights(request)) {
            filterChain.doFilter(request, response);

        } else {
            InputChecker.setServiceMessage(request, "access.error");
            response.sendRedirect(ServletPath.START_PAGE);
        }
    }

    boolean checkAccessRights(HttpServletRequest request) {
        AbstractAppUser user = (AbstractAppUser) request.getSession().getAttribute(USER);
        if (user == null) {
            return false;
        }
        String login = user.getLogin();

        return LOGGED_USERS.contains(login);
    }

    @Override
    public void destroy() {
    }
}