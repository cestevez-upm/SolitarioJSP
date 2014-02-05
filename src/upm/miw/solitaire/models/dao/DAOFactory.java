package upm.miw.solitaire.models.dao;

import org.apache.log4j.Logger;

import upm.miw.solitaire.models.dao.jdbc.JDBCDAOFactory;

public abstract class DAOFactory {
    public static DAOFactory factory = null;

    public static void setFactory(DAOFactory factory) {
        DAOFactory.factory = factory;
    }

    public static DAOFactory getFactory() {
        if (factory == null) {
            factory = new JDBCDAOFactory();
            Logger.getLogger(DAOFactory.class).info(
                    "DAOFactory: " + factory.getClass().getSimpleName());
        }
        return factory;
    }

    public abstract UserDAO getUserDAO();

}
