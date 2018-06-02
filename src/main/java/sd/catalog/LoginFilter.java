package sd.catalog;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = {"/pages/categories.xhtml", "/pages/users.xhtml", "/pages/sellers.xhtml"})
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig fc) throws ServletException {}

    @Inject
    private Instance<SessionBeanEJB> sessionBean;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (sessionBean.get().getActiveUser() != null) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/login.xhtml").forward(request, response);
        }

    }

    @Override
    public void destroy() {}
}