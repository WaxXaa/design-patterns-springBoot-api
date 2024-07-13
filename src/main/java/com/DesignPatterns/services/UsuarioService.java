package com.DesignPatterns.services;

import com.DesignPatterns.Conexion.Conexion;
import com.DesignPatterns.models.UsuarioDTO;

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
    public List<UsuarioDTO> opbtenerUsuarios() {
        try {
            Statement stm = conn.createStatement();
            String query = "SELECT * FROM USUARIOS";
            List<UsuarioDTO>listaUsuarios = new ArrayList<>();
            ResultSet res = stm.executeQuery(query);
            while (res.next()) {
                UsuarioDTO usuario = new UsuarioDTO(
                        res.getInt("id_usuario"),
                        res.getString("nombre"),
                        res.getString("apellido"),
                        res.getString("email"),
                        res.getString("contra"),
                        res.getString("foto_perfil"),
                        res.getInt("exp"));

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
    public int crearUsuario(UsuarioDTO usuario) {
        int resultado = 0;
        try {
            Statement stm = conn.createStatement();
            String query = "CALL crear_usuario(" +
                    usuario.getNombre() + "," +
                    usuario.getApellido() + "," +
                    usuario.getEmail() + "," +
                    usuario.getContra() + "," +
                    usuario.getFotoPerfil()+");";
            resultado = stm.executeUpdate(query);
            return resultado;
        } catch (SQLException e) {
            int i = 1;
        } catch (Exception e) {
            int i = 1;
        }

        return resultado;
    }
    public int actualizarUsuario(UsuarioDTO usuario) {
        int resultado = 0;
        try {
            Statement stm = conn.createStatement();
            String query = "CALL actualizar_usuario(" +
                    usuario.getNombre() + "," +
                    usuario.getApellido() + "," +
                    usuario.getEmail() + "," +
                    usuario.getContra() + "," +
                    usuario.getFotoPerfil()+
                    usuario.getExp()+");";
            resultado = stm.executeUpdate(query);
            return resultado;
        } catch (SQLException e){
            int i = 1;
        }  catch (Exception e){
            int i = 1;
        }
        return resultado;
    }
    public int eliminarUsuario(int id) {
        int resultado = 0;
        try {
            Statement stm = conn.createStatement();
            String query = "CALL eliminar_usuario("+id+");";
            return stm.executeUpdate(query);

        } catch (SQLException e){
            int i = 1;
        } catch (Exception e) {
            int i = 1;
        }
        return resultado;
    }
}
