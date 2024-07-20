package com.DesignPatterns.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    static public Connection connectar() throws Exception{
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mariadb://0.0.0.0:3306/juegos_senas", "root", "alejandro00");
        } catch (ClassNotFoundException e) {
            throw new Exception(e.getMessage() + " no se pudo cargar el driver ");
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
