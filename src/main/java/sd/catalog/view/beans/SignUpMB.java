package sd.catalog.view.beans;

import sd.catalog.SessionBeanEJB;
import sd.catalog.model.User;
import sd.catalog.service.UserService;
import sd.catalog.view.utils.FacesUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean
@ViewScoped
public class SignUpMB {

    @Inject
    private SessionBeanEJB sessionBean;

    @Inject
    private UserService userService;

    private User user = new User();

    public void createAccount() {

        userService.persist(user);

        sessionBean.setActiveUser(user);

        FacesUtils.reloadPage();

    }

    public User getUser() { return user; }

    public void setUser(User user) {
        this.user = user;
    }
}
