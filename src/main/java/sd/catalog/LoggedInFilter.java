package sd.catalog;

import sd.catalog.model.User;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "LoggedInFilter", urlPatterns = "/login.xhtml")
public class LoggedInFilter implements Filter {

    @Inject
    private Instance<SessionBeanEJB> sessionContext;

    @Override
    public void init(FilterConfig fc) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        User user = sessionContext.get().getActiveUser();

        if (user != null)
            request.getRequestDispatcher("/").forward(request, response);
        else
            chain.doFilter(request, response);


    }

    @Override
    public void destroy() {}
}