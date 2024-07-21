
package com.DesignPatterns.services;

import com.DesignPatterns.Conexion.Conexion;
import com.DesignPatterns.models.*;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminService {

    Connection conn;

    //public AdminService(Connection conn) {
      //  this.conn = AdminService.this.conn;
    //}

    // Métodos CRUD para la tabla Etapas

    public int createEtapa(String nombre, String descripcion) {
        int resultado = 0;
        try {
            conn = Conexion.connectar();
            assert conn != null;
            Statement stmt = conn.createStatement();
            String query = "CALL CreateEtapa('" + nombre + "', '" + descripcion + "')";
            stmt.execute(query);
            conn.close();
            return resultado;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public void readEtapa(int idEtapa) {
        try {
            conn = Conexion.connectar();
            String query = "CALL ReadEtapa(" + idEtapa + ")";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                System.out.println("Nombre: " + nombre + ", Descripción: " + descripcion);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateEtapa(int idEtapa, String nombre, String descripcion) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL UpdateEtapa(" + idEtapa + ", '" + nombre + "', '" + descripcion + "')";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteEtapa(int idEtapa) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL DeleteEtapa(" + idEtapa + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Métodos CRUD para la tabla Niveles

    public int createNivel(String nombre, String descripcion, int etapa, int exp) {
        int idNivel = -1;
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL CreateNivel('" + nombre + "', '" + descripcion + "', " + etapa + ", " + exp + ")";
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                idNivel = rs.getInt(1);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return idNivel;
    }

    public void readNivel(int idNivel) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL ReadNivel(" + idNivel + ")";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                int etapa = rs.getInt("etapa");
                int exp = rs.getInt("exp");
                System.out.println("Nombre: " + nombre + ", Descripción: " + descripcion + ", Etapa: " + etapa + ", EXP: " + exp);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateNivel(int idNivel, String nombre, String descripcion, int etapa, int exp) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL UpdateNivel(" + idNivel + ", '" + nombre + "', '" + descripcion + "', " + etapa + ", " + exp + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteNivel(int idNivel) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL DeleteNivel(" + idNivel + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Métodos CRUD para la tabla Lecciones

    public int createLeccion(String nombre, String descripcion, String imagenUrl, String videoUrl, int nivel) {
        int idLeccion = -1;
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL CreateLeccion('" + nombre + "', '" + descripcion + "', '" + imagenUrl + "', '" + videoUrl + "', " + nivel + ")";
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                idLeccion = rs.getInt(1);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return idLeccion;
    }

    public void readLeccion(int idLeccion) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL ReadLeccion(" + idLeccion + ")";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String imagenUrl = rs.getString("imagen_url");
                String videoUrl = rs.getString("video_url");
                int nivel = rs.getInt("nivel");
                System.out.println("Nombre: " + nombre + ", Descripción: " + descripcion + ", Imagen URL: " + imagenUrl + ", Video URL: " + videoUrl + ", Nivel: " + nivel);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateLeccion(int idLeccion, String nombre, String descripcion, String imagenUrl, String videoUrl, int nivel) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL UpdateLeccion(" + idLeccion + ", '" + nombre + "', '" + descripcion + "', '" + imagenUrl + "', '" + videoUrl + "', " + nivel + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteLeccion(int idLeccion) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL DeleteLeccion(" + idLeccion + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Métodos CRUD para la tabla Tipo_Preguntas

    public int createTipoPregunta(String tipo) {
        int idTipo = -1;
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL CreateTipoPregunta('" + tipo + "')";
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                idTipo = rs.getInt(1);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return idTipo;
    }

    public void readTipoPregunta(int idTipo) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL ReadTipoPregunta(" + idTipo + ")";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String tipo = rs.getString("tipo");
                System.out.println("Tipo: " + tipo);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTipoPregunta(int idTipo, String tipo) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL UpdateTipoPregunta(" + idTipo + ", '" + tipo + "')";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTipoPregunta(int idTipo) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL DeleteTipoPregunta(" + idTipo + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Métodos CRUD para la tabla Preguntas

    public int createPregunta(String pregunta, String imagenUrl, int nivel, int tipo) {
        int idPregunta = -1;
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL CreatePregunta('" + pregunta + "', '" + imagenUrl + "', " + nivel + ", " + tipo + ")";
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                idPregunta = rs.getInt(1);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return idPregunta;
    }

    public void readPregunta(int idPregunta) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL ReadPregunta(" + idPregunta + ")";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String pregunta = rs.getString("pregunta");
                String imagenUrl = rs.getString("imagen_url");
                int nivel = rs.getInt("nivel");
                int tipo = rs.getInt("tipo");
                System.out.println("Pregunta: " + pregunta + ", Imagen URL: " + imagenUrl + ", Nivel: " + nivel + ", Tipo: " + tipo);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePregunta(int idPregunta, String pregunta, String imagenUrl, int nivel, int tipo) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL UpdatePregunta(" + idPregunta + ", '" + pregunta + "', '" + imagenUrl + "', " + nivel + ", " + tipo + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePregunta(int idPregunta) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL DeletePregunta(" + idPregunta + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Métodos CRUD para la tabla Respuestas

    public int createRespuesta(String respuesta, String imagenUrl, boolean correcta, boolean tipo, int pregunta) {
        int idRespuesta = -1;
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL CreateRespuesta('" + respuesta + "', '" + imagenUrl + "', " + correcta + ", " + tipo + ", " + pregunta + ")";
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                idRespuesta = rs.getInt(1);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return idRespuesta;
    }

    public void readRespuesta(int idRespuesta) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL ReadRespuesta(" + idRespuesta + ")";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String respuesta = rs.getString("respuesta");
                String imagenUrl = rs.getString("imagenURL");
                boolean correcta = rs.getBoolean("correcta");
                boolean tipo = rs.getBoolean("tipo");
                int pregunta = rs.getInt("pregunta");
                System.out.println("Respuesta: " + respuesta + ", Imagen URL: " + imagenUrl + ", Correcta: " + correcta + ", Tipo: " + tipo + ", Pregunta: " + pregunta);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateRespuesta(int idRespuesta, String respuesta, String imagenUrl, boolean correcta, boolean tipo, int pregunta) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL UpdateRespuesta(" + idRespuesta + ", '" + respuesta + "', '" + imagenUrl + "', " + correcta + ", " + tipo + ", " + pregunta + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteRespuesta(int idRespuesta) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL DeleteRespuesta(" + idRespuesta + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Métodos CRUD para la tabla Orden_Respuestas

    public int createOrdenRespuesta(String respuesta, int pregunta, String imagenUrl, int orden) {
        int idOrden = -1;
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL CreateOrdenRespuesta('" + respuesta + "', " + pregunta + ", '" + imagenUrl + "', " + orden + ")";
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                idOrden = rs.getInt(1);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return idOrden;
    }

    public void readOrdenRespuesta(int idOrden) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL ReadOrdenRespuesta(" + idOrden + ")";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String respuesta = rs.getString("respuesta");
                int pregunta = rs.getInt("pregunta");
                String imagenUrl = rs.getString("imagenURL");
                int orden = rs.getInt("orden");
                System.out.println("Respuesta: " + respuesta + ", Pregunta: " + pregunta + ", Imagen URL: " + imagenUrl + ", Orden: " + orden);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateOrdenRespuesta(int idOrden, String respuesta, int pregunta, String imagenUrl, int orden) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL UpdateOrdenRespuesta(" + idOrden + ", '" + respuesta + "', " + pregunta + ", '" + imagenUrl + "', " + orden + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteOrdenRespuesta(int idOrden) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL DeleteOrdenRespuesta(" + idOrden + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Métodos CRUD para la tabla Tipo_Usuario

    public void createTipoUsuario(int idTipo, String tipo) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL CreateTipoUsuario(" + idTipo + ", '" + tipo + "')";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void readTipoUsuario(int idTipo) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL ReadTipoUsuario(" + idTipo + ")";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String tipo = rs.getString("tipo");
                System.out.println("Tipo: " + tipo);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTipoUsuario(int idTipo, String tipo) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL UpdateTipoUsuario(" + idTipo + ", '" + tipo + "')";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTipoUsuario(int idTipo) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL DeleteTipoUsuario(" + idTipo + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Métodos CRUD para la tabla Usuarios

    public int createUsuario(String nombre, String apellido, String email, String contra, String fotoPerfil, int tipo) {
        int idUsuario = -1;
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL CreateUsuario('" + nombre + "', '" + apellido + "', '" + email + "', '" + contra + "', '" + fotoPerfil + "', " + tipo + ")";
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                idUsuario = rs.getInt(1);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idUsuario;
    }

    public void readUsuario(int idUsuario) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL ReadUsuario(" + idUsuario + ")";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String contra = rs.getString("contra");
                String fotoPerfil = rs.getString("foto_perfil");
                int tipo = rs.getInt("tipo");
                System.out.println("Nombre: " + nombre + ", Apellido: " + apellido + ", Email: " + email + ", Contraseña: " + contra + ", Foto de Perfil: " + fotoPerfil + ", Tipo: " + tipo);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUsuario(int idUsuario, String nombre, String apellido, String email, String contra, String fotoPerfil, int tipo, int exp) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL UpdateUsuario(" + idUsuario + ", '" + nombre + "', '" + apellido + "', '" + email + "', '" + contra + "', '" + fotoPerfil + "', " + tipo + ", " + exp + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUsuario(int idUsuario) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL DeleteUsuario(" + idUsuario + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Métodos CRUD para la tabla User_Etapa

    public void createUserEtapa(int idUser, int idEtapa) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL CreateUserEtapa(" + idUser + ", " + idEtapa + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void readUserEtapa(int idUser, int idEtapa) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL ReadUserEtapa(" + idUser + ", " + idEtapa + ")";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                // Aquí podrías leer y mostrar información si es necesario
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUserEtapa(int idUser, int idEtapa) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL UpdateUserEtapa(" + idUser + ", " + idEtapa + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUserEtapa(int idUser, int idEtapa) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL DeleteUserEtapa(" + idUser + ", " + idEtapa + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

