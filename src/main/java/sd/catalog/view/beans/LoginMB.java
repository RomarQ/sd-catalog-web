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
public class LoginMB {

    @Inject
    private SessionBeanEJB sessionBean;

    @Inject
    UserService userService;

    User user = new User();

    public void login() {

        User auth_user = userService.authenticate(user.getEmail() , user.getPassword());

        sessionBean.setActiveUser(auth_user);

        FacesUtils.reloadPage();

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
