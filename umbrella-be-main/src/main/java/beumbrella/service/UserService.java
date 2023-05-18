package beumbrella.service;

import beumbrella.model.User;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    void save(User user);

    Iterable<User> findAll();
    Iterable<User> findAllByAdmin();

    User findByUsername(String username);

    User getCurrentUser();

    Optional<User> findById(Long id);

    UserDetails loadUserById(Long id);

    boolean checkLogin(User user);

    boolean isRegister(User user);

    boolean isCorrectConfirmPassword(User user);

    void remove(Long id);

    Iterable<User> findByName(String name);

    Optional<User> lockedUser(Long id);

    Iterable<User> findAllExcept(Long id);

    Iterable<User> findCustomerByPhone(String phone, Long id);

}
