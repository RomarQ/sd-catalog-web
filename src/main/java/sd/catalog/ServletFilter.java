package sd.catalog;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "FilterLogin", urlPatterns = {"/pages/*"})
public class ServletFilter implements Filter {

    @Inject
    private Instance<SessionContext> sessionContext;

    @Override
    public void init(FilterConfig fc) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (sessionContext.get().getActiveUser() != null) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/login.xhtml").forward(request, response);
        }

    }

    @Override
    public void destroy() {}
}