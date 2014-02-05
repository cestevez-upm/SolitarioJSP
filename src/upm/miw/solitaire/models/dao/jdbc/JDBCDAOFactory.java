package upm.miw.solitaire.models.dao.jdbc;

import upm.miw.solitaire.models.dao.DAOFactory;
import upm.miw.solitaire.models.dao.UserDAO;

public class JDBCDAOFactory extends DAOFactory {

    @Override
    public UserDAO getUserDAO() {
        return new JDBCUserDAO();
    }
}