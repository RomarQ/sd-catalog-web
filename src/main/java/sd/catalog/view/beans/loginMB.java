package sd.catalog.view.beans;

import sd.catalog.SessionContext;
import sd.catalog.model.User;
import sd.catalog.service.UserService;
import sd.catalog.view.utils.FacesUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;



@ManagedBean
@ViewScoped
public class loginMB {

    @Inject
    UserService userService;

    @Inject
    SessionContext sessionContext;

    User user = new User();

    public void login() {

        User auth_user = userService.authenticate(user.getEmail() , user.getPassword());

        sessionContext.setActiveUser(auth_user);

        FacesUtils.reloadPage();

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void buttonAction(ActionEvent actionEvent) {

    }

}
