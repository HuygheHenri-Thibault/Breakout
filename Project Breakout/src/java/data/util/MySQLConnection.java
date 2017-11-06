/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import util.BreakoutException;

/**
 *
 * @author Henri
 */
public class MySQLConnection {
    private static final String URL = "jdbc:mysql://localhost/breakout";
    private static final String UID = "root"; // Dios mio don't let teachers see this
    private static final String PWD = ""; // or this
    
    static
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException ex)
        {
            throw new BreakoutException("Unable to load database driver.", ex);
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, UID, PWD);
    }
}
