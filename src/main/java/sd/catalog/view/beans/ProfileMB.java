package sd.catalog.view.beans;

import sd.catalog.SessionBeanEJB;
import sd.catalog.model.User;
import sd.catalog.service.UserService;
import sd.catalog.view.utils.FacesUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean
@ViewScoped
public class ProfileMB {

    @Inject
    private SessionBeanEJB sessionBean;

    @Inject
    private UserService userService;

    private User user;

    @PostConstruct
    public void init() { user = sessionBean.getActiveUser(); }

    public void updateProfile() {

        userService.update(user);

        FacesUtils.addMessageSaveSuccess();

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFullName() {
        return user.getFirstName() + " " + user.getLastName();
    }
}
