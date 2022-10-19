package ru.job4j.dreamjob.store;

import net.jcip.annotations.ThreadSafe;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Alex_life
 * @version 2.0
 * @since 18.10.2022
 */
@ThreadSafe
@Repository
public class UserDBStore {
    private final BasicDataSource pool;
    private static final Logger LOG = LoggerFactory.getLogger(UserDBStore.class.getName());
    private static final String SQL_SELECT_ALL = "SELECT * FROM users ORDER BY id";
    private static final String SQL_INSERT =
            "INSERT INTO users(name, email, password, created) VALUES (?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE users SET name = ?, email = ?, password = ? WHERE id = ?";

    private static final String SQL_FIND_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SQL_FIND_NAME = "SELECT * FROM users WHERE name = ?";
    private static final String SQL_FIND_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String SQL_FIND_NAME_AND_EMAIL =
            "SELECT * FROM users WHERE users.email LIKE ? AND users.password LIKE ?;";

    public UserDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_SELECT_ALL)) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    users.add(createNewUser(it));
                }
            }
        } catch (Exception e) {
            LOG.error("UserDBStore. Ошибка в методе findAll - ", e);
        }
        return users;
    }

    public Optional<User> addUser(User user) {
        Optional<User> check = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setTimestamp(4, Timestamp.valueOf(user.getCreated()));
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    user.setId(id.getInt(1));
                }
            }
            check = Optional.of(user);
        } catch (Exception e) {
            LOG.error("UserDBStore. Ошибка в методе addUser - ", e);
        }
        return check;
    }

    public User findByIdUser(int id) {
        User user = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(SQL_FIND_ID)) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    user = createNewUser(it);
                }
            }
        } catch (Exception e) {
            LOG.error("UserDBStore. Ошибка в методе findByIdUser - ", e);
        }
        return user;
    }

    public User findByNameUser(String name) {
        User user = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(SQL_FIND_NAME)) {
            ps.setString(1, name);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    user = createNewUser(it);
                }
            }
        } catch (Exception e) {
            LOG.error("UserDBStore. Ошибка в методе findByNameUser - ", e);
        }
        return user;
    }

    public User findByEmailUser(String email) {
        User user = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(SQL_FIND_EMAIL)) {
            ps.setString(1, email);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    user = createNewUser(it);
                }
            }
        } catch (Exception e) {
            LOG.error("UserDBStore. Ошибка в методе findByEmailUser - ", e);
        }
        return user;
    }

    public void updateUser(User user) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_UPDATE)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getId());
            ps.execute();
        } catch (Exception e) {
            LOG.error("UserDBStore. Ошибка в методе updateUser - ", e);
        }
    }

    public void delete(User user) {
    }

    private User createNewUser(ResultSet it) throws SQLException {
        return new User(
                it.getInt("id"),
                it.getString("name"),
                it.getString("email"),
                it.getString("password"),
                it.getTimestamp("created").toLocalDateTime()
        );
    }

    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        Optional<User> check = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(SQL_FIND_NAME_AND_EMAIL)) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    check = Optional.of(createNewUser(it));
                }
            }
        } catch (Exception e) {
            LOG.error("UserDBStore. Ошибка в методе findUserByEmailAndPassword - ", e);
        }
        return check;
    }
}
