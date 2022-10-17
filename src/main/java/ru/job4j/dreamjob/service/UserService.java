package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.persistence.UserDBStore;

import java.util.Collection;

/**
 * Сервис описывающий логику поведения пользователя
 *
 * @author Alex_life
 * @version 1.0
 * @since 16.10.2022
 */
@ThreadSafe
@Service
public class UserService {
    private final UserDBStore storeUser;

    public UserService(UserDBStore store) {
        this.storeUser = store;
    }

    public Collection<User> findAll() {
        return storeUser.findAll();
    }

    public void addUser(User user) {
        storeUser.addUser(user);
    }

    public User findByIdUser(int id) {
        return storeUser.findByIdUser(id);
    }

    public User findByNameUser(String name) {
        return storeUser.findByNameUser(name);
    }

    public User findByEmailUser(String email) {
        return storeUser.findByEmailUser(email);
    }

    public void update(User user) {
        storeUser.updateUser(user);
    }

    public void delete(User user) {
        storeUser.delete(user);
    }

}
