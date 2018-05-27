package sd.catalog;

import sd.catalog.model.User;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class SessionContext implements Serializable {

    private User activeUser;

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

}
