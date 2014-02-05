package upm.miw.solitaire.models.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ump.miw.solitaire.models.entities.Gender;
import ump.miw.solitaire.models.entities.User;
import upm.miw.solitaire.models.dao.UserDAO;

public class JDBCUserDAO extends JDBCGenericDAO<User, String> implements UserDAO {
    private Logger log = Logger.getLogger(JDBCUserDAO.class);

    @Override
    public void createTable() {
        ContextJDBC.getJDBC().update("DROP TABLE IF EXISTS user ");
        ContextJDBC.getJDBC().update(
                "CREATE TABLE user (nick VARCHAR(99) NOT NULL, password VARCHAR(99),"
                        + "firstName VARCHAR(99),lastName VARCHAR(99),age INT,gender VARCHAR(99),"
                        + "country VARCHAR(99), administrator BIT, PRIMARY KEY (nick))");
        log.info("create Table...");
    }

    @Override
    public void create(User user) {
        log.info("create usuario:" + user);
        ContextJDBC.getJDBC().update(
                "INSERT user VALUES ('" + user.getNick() + "','" + user.getPassword()
                        + "','" + user.getFirstName() + "','" + user.getLastName() + "',"
                        + user.getAge() + ",'" + user.getGender() + "','" + user.getCountry() + "',"
                        + user.isAdministrator() + ")");
    }

    @Override
    public User read(String nick) {
        User usr = null;
        ResultSet rs = ContextJDBC.getJDBC().query("SELECT * FROM user WHERE nick='" + nick +"'");
        try {
            if (rs != null && rs.next()) {
                usr = new User(rs.getString("nick"), rs.getString("password"),
                        rs.getString("firstName"), rs.getString("lastName"), rs.getInt("age"),
                        Gender.valueOf(rs.getString("gender")), rs.getString("country"));
                usr.setAdministrator(rs.getBoolean("administrator"));
            }
        } catch (SQLException e) {
            log.error("read (" + nick + ")" + e.getMessage());
        }
        return usr;
    }

    @Override
    public void update(User user) {
        ContextJDBC.getJDBC().update(
                "UPDATE user SET password='" + user.getPassword() + "',firstName='"
                        + user.getFirstName() + "',lastName='" + user.getLastName()
                        + "',age=" + user.getAge() + "',gender='" + user.getGender()
                        + "',country='" + user.getGender() + "' WHERE nick='" + user.getNick()
                        + "'");
    }

    @Override
    public void delete(User user) {
        this.deleteByID(user.getNick());
    }

    @Override
    public void deleteByID(String id) {
        ContextJDBC.getJDBC().update("DELETE FROM user WHERE nick=" + id);
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<User>();
        ResultSet rs = ContextJDBC.getJDBC().query("SELECT * FROM user");
        try {
            while (rs.next()) {
                User usr = new User(rs.getString("nick"), rs.getString("password"),
                        rs.getString("firstName"), rs.getString("lastName"), rs.getInt("age"),
                        Gender.valueOf(rs.getString("gender")), rs.getString("country"));
                usr.setAdministrator(rs.getBoolean("administrator"));
                users.add(usr);
            }
        } catch (SQLException e) {
            log.error("findAll : " + e.getMessage());
        }
        return users;
    }

    public static void main(String[] args) {
        JDBCUserDAO dao= new JDBCUserDAO();
        dao.createTable();
        User admin = new User("admin", "a", "admin", "", 69, Gender.MALE, "Madrid");
        admin.setAdministrator(true);
        dao.create(admin);
        dao.create(new User("user", "u", "user", "", 69, Gender.MALE, "Madrid"));
    }
}
