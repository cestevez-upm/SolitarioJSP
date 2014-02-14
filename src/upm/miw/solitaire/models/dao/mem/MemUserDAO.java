package upm.miw.solitaire.models.dao.mem;

import upm.miw.solitaire.models.dao.UserDAO;
import upm.miw.solitaire.models.entities.Gender;
import upm.miw.solitaire.models.entities.User;

public class MemUserDAO extends MemGenericDAO<User, String> implements UserDAO {

    public MemUserDAO() {
        User admin = new User("admin", "a", "admin", "", 69, Gender.MALE, "Madrid");
        admin.setAdministrator(true);
        this.create(admin);
        this.create(new User("user", "u", "user", "", 69, Gender.MALE, "Madrid"));
    }

    @Override
    protected String getId(User entity) {
        return entity.getNick();
    }

}
