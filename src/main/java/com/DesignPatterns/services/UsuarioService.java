package com.DesignPatterns.services;

import com.DesignPatterns.Conexion.Conexion;
import com.DesignPatterns.models.Usuario_set_get;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    Connection conn;
    public UsuarioService() {
        conn = Conexion.connectar();
    }
    public List<Usuario_set_get> opbtenerUsuarios() {
        try {
            Statement stm = conn.createStatement();
            String query = "SELECT * FROM USUARIOS";
            List<Usuario_set_get>listaUsuarios = new ArrayList<>();
            ResultSet res = stm.executeQuery(query);
            while (res.next()) {
                Usuario_set_get usuario = new Usuario_set_get(
                        res.getInt("id_usuario"),
                        res.getString("nombre"),
                        res.getString("apellido"),
                        res.getString("email"),
                        res.getString("contra"),
                        res.getString("foto_perfil"));

                listaUsuarios.add(usuario);

            }
            return listaUsuarios;

        } catch (SQLException e) {
            int i = 1;
        }catch (Exception e) {
            int i = 1;
        }
        return null;

    }
    public int crearUsuario(Usuario_set_get usuario) {
        int resultado = 0;
        try {
            Statement stm = conn.createStatement();
            String query = "CALL crear_usuario(" +
                    usuario.getNombre() + "," +
                    usuario.getApellido() + "," +
                    usuario.getEmail() + "," +
                    usuario.getContra() + "," +
                    1 + ");";
            resultado = stm.executeUpdate(query);
            return resultado;
        } catch (SQLException e) {
            int i = 1;
        } catch (Exception e) {
            int i = 1;
        }

        return resultado;
    }
}
