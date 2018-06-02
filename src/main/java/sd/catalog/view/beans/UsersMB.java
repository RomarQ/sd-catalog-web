package sd.catalog.view.beans;

import sd.catalog.SessionBeanEJB;
import sd.catalog.customExeception.CustomMessageException;
import sd.catalog.model.User;
import sd.catalog.model.UserRole;
import sd.catalog.repository.UserRepository;
import sd.catalog.service.UserService;
import sd.catalog.view.utils.FacesUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class UsersMB {

    @Inject
    private SessionBeanEJB sessionBean;

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserService userService;

    private List<User> users;

    @PostConstruct
    @PostUpdate
    @PostRemove
    public void init() {
        loadUsers();
    }

    public void loadUsers() {
        users = userRepository.findAll();
    }


    public void removeUser(User u) {

        if (!sessionBean.getActiveUser().getRole().equals(UserRole.ADMIN))
            throw new CustomMessageException("You don't have permissions to delete this user!");

        userService.remove(u);

        loadUsers();

        FacesUtils.addMessageSuccess("User "+u.getEmail()+" was successfully removed!");
    }

    public void saveUser(User u) {

        userService.update(u);

        loadUsers();

        FacesUtils.addMessageSuccess("User "+u.getEmail()+" was successfully updated!");
    }

    public List<User> getUsers() {
        return users;
    }

    public ArrayList<String> getUserRoles() {

        ArrayList<String> roles = new ArrayList<>();

        for(UserRole role : UserRole.values())
            roles.add(role.toString());

        return roles;
    }

}