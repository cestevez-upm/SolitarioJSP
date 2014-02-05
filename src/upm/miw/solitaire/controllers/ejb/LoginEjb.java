package upm.miw.solitaire.controllers.ejb;

import ump.miw.solitaire.models.entities.User;
import upm.miw.solitaire.models.dao.DAOFactory;
import upm.miw.solitaire.models.dao.UserDAO;

public class LoginEjb {

    /**
     * @param nick
     * @param password
     * @return el usuario, si no lo encuentra, o la contraseña es incorrecta,
     *         devuelve null
     */
    public User login(String nick, String password) {
        UserDAO userDAO = DAOFactory.getFactory().getUserDAO();
        User usr = userDAO.read(nick);
        if (usr == null || !password.equals(usr.getPassword()))
            return null;
        else
            return usr;
    }

    /**
     * @param usuario
     * @return true si se puede crear el usuario, false si el nick está ocupado
     */
    public boolean register(User usuario) {
        UserDAO userDAO = DAOFactory.getFactory().getUserDAO();
        User usr = userDAO.read(usuario.getNick());
        if (usr == null) {
            userDAO.create(usuario);
            return true;
        }
        return false;
    }

}
