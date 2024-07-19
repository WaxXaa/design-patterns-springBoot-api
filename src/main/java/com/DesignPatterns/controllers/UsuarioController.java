package com.DesignPatterns.controllers;

import com.DesignPatterns.models.Usuario;
import com.DesignPatterns.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {
    @GetMapping("/users/all")
    public ResponseEntity<List<Usuario>> obtenerUsuariosController() {
        try {
            List<Usuario> listUsuarios = new UsuarioService().obtenerUsuarios();
            return new ResponseEntity<List<Usuario>>(listUsuarios,HttpStatus.OK);//http status code 200
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users/registrar")
    public ResponseEntity<String> registrarUsuarioController(@RequestBody Usuario usuario) {
        try {
            int resultado = new UsuarioService().crearUsuario(usuario);
            if(resultado == 0) {throw new Exception("no se puedo actualizar el usuario");}
            return new ResponseEntity<String>("usuario registrado exitosamente", HttpStatus.CREATED);// http status 201
        }catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/user/actualizar")
    public ResponseEntity<String> actualizarUsuarioController(@RequestBody Usuario usuario) {
        try {
            int resultado = new UsuarioService().actualizarUsuario(usuario);
            if(resultado == 0) {throw new Exception("no se puedo crear el usuario");}
            return new ResponseEntity<String>("usuario actualizado exitosamente", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("user/eliminar/{idUsuario}")
    public ResponseEntity<String> eliminarUsuarioController(@PathVariable("idUsuario") int idUsuario) {
        try {
            int resultado = new UsuarioService().eliminarUsuario(idUsuario);
            if(resultado == 0) {throw new Exception("no se puedo eliminar el usuario");}
            return new ResponseEntity<String>("usuario eliminado exitosamente", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
