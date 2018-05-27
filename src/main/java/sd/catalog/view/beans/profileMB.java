package sd.catalog.view.beans;

import sd.catalog.SessionContext;
import sd.catalog.model.User;
import sd.catalog.service.UserService;
import sd.catalog.view.utils.FacesUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean
@ViewScoped
public class profileMB {

    @Inject
    private UserService userService;

    @Inject
    private SessionContext sessionContext;

    private User user;

    @PostConstruct
    public void init() { user = sessionContext.getActiveUser(); }

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
