package com.DesignPatterns.services;

import com.DesignPatterns.Conexion.Conexion;
import com.DesignPatterns.exceptions.DataBaseException;
import com.DesignPatterns.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    Connection conn;

    public String conectar()  {
        try {
            int i = 0;
            conn=Conexion.connectar();
            if (conn == null) {
                return "con esta null";
            }
            Statement stm = conn.createStatement();
            i = stm.executeUpdate("INSERT INTO Etapas (nombre, descripcion) values ('etapa1','la primera');");


            return "i     " + conn.getClientInfo() + " no esta null " ;
        } catch (Exception e){
            return e.getMessage() + "error service";
        }
    }
//    public UsuarioService() {
//        conn = Conexion.connectar();
//    }
    public List<Usuario> obtenerUsuarios() throws Exception {
        try {
            conn = Conexion.connectar();
            assert conn != null;
            Statement stm = conn.createStatement();
            String query = "SELECT * FROM USUARIOS";
            List<Usuario>listaUsuarios = new ArrayList<>();
            ResultSet res = stm.executeQuery(query);
            while (res.next()) {
                Usuario usuario = new Usuario(
                        res.getInt("id_usuario"),
                        res.getString("nombre"),
                        res.getString("apellido"),
                        res.getString("email"),
                        res.getString("contra"),
                        res.getString("foto_perfil"),
                        res.getInt("exp"),
                        res.getInt("tipo"));

                listaUsuarios.add(usuario);

            }
            return listaUsuarios;

        } catch (SQLException e){
            throw new DataBaseException(e.getMessage());
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public int crearUsuario(Usuario usuario) throws Exception{
        int resultado = 0;
        try {
            conn = Conexion.connectar();
            assert conn != null;
            Statement stm = conn.createStatement();
            String query = "CALL crear_usuario('" +
                    usuario.getNombre() + "','" +
                    usuario.getApellido() + "','" +
                    usuario.getEmail() + "','" +
                    usuario.getContra() + "','" +
                    usuario.getFotoPerfil()+"',"+
                    1+");";
            resultado = stm.executeUpdate(query);
            conn.close();
            return resultado;
        }catch (SQLException e){
            throw new DataBaseException(e.getMessage());
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public int actualizarUsuario(Usuario usuario) throws Exception {
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
            throw new DataBaseException(e.getMessage());
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public int eliminarUsuario(int idUsuario) throws Exception {
        int resultado = 0;
        try {
            conn = Conexion.connectar();
            Statement stm = conn.createStatement();
            String query = "CALL eliminar_usuario("+idUsuario+");";
            return stm.executeUpdate(query);

        } catch (SQLException e){
            throw new DataBaseException(e.getMessage());
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public Usuario obtenerUsuario(int idUsuario) throws Exception {
        try (Connection conn = Conexion.connectar()) {
            assert conn != null;
            try (Statement stm = conn.createStatement()) {
                String query = "SELECT * FROM USUARIOS WHERE id_usuario = " + idUsuario + ";";
                ResultSet res = stm.executeQuery(query);
                if (res.next()) {
                    return new Usuario(
                            res.getInt("id_usuario"),
                            res.getString("nombre"),
                            res.getString("apellido"),
                            res.getString("email"),
                            res.getString("contra"),
                            res.getString("foto_perfil"),
                            res.getInt("exp"),
                            res.getInt("tipo"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public int iniciarSesion(String correo, String contra) throws Exception {
        int cod_usuario = -1;
        String query = "{CALL IniciarSesion(?, ?, ?)}";

        try {Connection conn = Conexion.connectar();
             CallableStatement stmt = conn.prepareCall(query);

            stmt.setString(1, correo);
            stmt.setString(2, contra);
            stmt.registerOutParameter(3, java.sql.Types.INTEGER);

            stmt.execute();

            cod_usuario = stmt.getInt(3);
            conn.close();
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e);
        }

        return cod_usuario;
    }

    public List<Usuario> listarRanking() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM Usuarios ORDER BY exp DESC LIMIT 10";

        try {Connection conn = Conexion.connectar();
            assert conn != null;
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet res = stmt.executeQuery()) {

                while (res.next()) {
                    Usuario usuario = new Usuario(
                            res.getInt("id_usuario"),
                            res.getString("nombre"),
                            res.getString("apellido"),
                            res.getString("email"),
                            res.getString("contra"),
                            res.getString("foto_perfil"),
                            res.getInt("exp"),
                            res.getInt("tipo"));
                    usuarios.add(usuario);
                }
                conn.close();

            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }

        return usuarios;
    }
}
