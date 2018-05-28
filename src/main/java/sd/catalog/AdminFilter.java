package sd.catalog;

import sd.catalog.model.User;
import sd.catalog.model.UserRole;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter", urlPatterns = {"/pages/sellers.xhmtl","/pages/categories.xhmtl"})
public class AdminFilter implements Filter {

    @Inject
    private Instance<SessionContext> sessionContext;

    @Override
    public void init(FilterConfig fc) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        User user = sessionContext.get().getActiveUser();

        if (user != null)
            if ( user.getRole().equals(UserRole.ADMIN))
                chain.doFilter(request, response);
        else
            request.getRequestDispatcher("/").forward(request, response);

    }

    @Override
    public void destroy() {}
}