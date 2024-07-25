package com.DesignPatterns.Conexion;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    static public Connection connectar() throws Exception{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER_NAME");
            String pass = System.getenv("DB_PASSWORD");
            return DriverManager.getConnection(url,user, pass);
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
