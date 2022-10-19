package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.store.UserDBStore;

import java.util.Collection;
import java.util.Optional;

/**
 * Сервис описывающий логику поведения пользователя
 *
 * @author Alex_life
 * @version 2.0
 * @since 18.10.2022
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

    public Optional<User> addUser(User user) {
        return storeUser.addUser(user);
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

    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        return storeUser.findUserByEmailAndPassword(email, password);
    }


}
