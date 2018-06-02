package sd.catalog;

import sd.catalog.model.User;

import javax.ejb.Stateful;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Stateful
@SessionScoped
public class SessionBeanEJB implements Serializable {

    private User activeUser;

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

}
