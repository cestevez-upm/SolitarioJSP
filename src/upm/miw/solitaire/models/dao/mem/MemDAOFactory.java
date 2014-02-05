package upm.miw.solitaire.models.dao.mem;

import upm.miw.solitaire.models.dao.DAOFactory;
import upm.miw.solitaire.models.dao.UserDAO;

public class MemDAOFactory extends DAOFactory {
	private UserDAO memUsuarioDAO = new MemUserDAO();

    @Override
    public UserDAO getUserDAO() {
        return this.memUsuarioDAO;
    }

}
