package sd.catalog.view.beans;

import sd.catalog.model.User;
import sd.catalog.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@ViewScoped
public class UsersMB {

    @Inject
    private UserRepository userRepository;

    private List<User> users;

    @PostConstruct
    public void init() {
        loadUsers();
    }

    public void loadUsers() {
        users = userRepository.findAll();
    }

    public List<User> getUsers() {
        return users;
    }

}