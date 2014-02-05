package upm.miw.solitaire.models.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class ContextJDBC {
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static final String URL = "jdbc:mysql://localhost:3306/";

    private static final String BD = "jee";

    private static final String USER = "root";

    private static final String PASS = "";

    private static ContextJDBC context = null;

    private Logger log = Logger.getLogger(ContextJDBC.class);

    private Statement statement = null;

    private Connection connection;

    public ContextJDBC() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL + BD, USER, PASS);
            this.statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            log.error("Problemas con el driver: " + e.getMessage());
        } catch (SQLException e) {
            log.error("Problemas con la BD: " + e.getMessage());
        }
        log.info("Conectado: BD:" + BD + " USER:" + USER + " PASS:****");
    }

    protected static ContextJDBC getJDBC() {
        if (context == null)
            context = new ContextJDBC();
        try {
            if (!context.connection.isValid(0))
                context = new ContextJDBC();
        } catch (SQLException e) {
            context = new ContextJDBC();
        }
        return context;
    }

    public ResultSet query(String sql) {
        try {
            return this.statement.executeQuery(sql);
        } catch (SQLException e) {
            log.error("Query SQL: " + sql + ">>>" + e.getMessage());
        }
        return null;
    }

    public boolean update(String sql) {
        try {
            this.statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            log.error("Update SQL: " + sql + ">>>" + e.getMessage());
        }
        return false;
    }

}
