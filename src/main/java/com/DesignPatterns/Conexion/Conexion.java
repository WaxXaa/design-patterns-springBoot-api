package com.DesignPatterns.Conexion;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    static public Connection connectar() throws Exception{
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://mariadb-17648-0.cloudclusters.net:17664/manitas";
            String user = "hampao";
            String pass = "3siumai1$";
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
