package sd.catalog.service;

import sd.catalog.customExeception.CustomMessageException;
import sd.catalog.model.User;
import sd.catalog.repository.ProductRepository;
import sd.catalog.repository.UserRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;

public class UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private ProductRepository productRepository;

    @Transactional
    public void persist(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new CustomMessageException("This Email has already been taken!");
        }

        userRepository.persist(user);
    }

    @Transactional
    public void remove(User u) {
        productRepository.removeByUser(u);
        userRepository.remove(u);
    }

    @Transactional
    public User update(User user) {
        return userRepository.update(user);
    }

    public User authenticate(String email, String password) {

        User user = userRepository.findByEmail(email);

        if ( user != null && user.getPassword().equals(password)) {
            return user;
        }

        throw new CustomMessageException("Email or Password are invalid!");
    }
}
