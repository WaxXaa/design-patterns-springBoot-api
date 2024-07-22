package com.DesignPatterns.controllers;

import com.DesignPatterns.models.Etapas;
import com.DesignPatterns.models.Lecciones;
import com.DesignPatterns.models.PreguntasDTO;
import com.DesignPatterns.models.Usuario;
import com.DesignPatterns.services.JuegoService;
import com.DesignPatterns.services.UsuarioService;
import com.DesignPatterns.services.admin.LeccionesService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JuegoController {
    @GetMapping("etapas/all")
    public ResponseEntity<List<Etapas>> obtenerEtapasController(){
        try {
            List<Etapas> etapas = new JuegoService().obtenerEtapas();
            return new ResponseEntity<List<Etapas>>(etapas,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("etapas/niveles")
    public ResponseEntity<List<Etapas>> obtenerEtapasNivelesController(){
        try {
            List<Etapas> etapasNiveles = new JuegoService().obtenerEtapasNiveles();
            return new ResponseEntity<List<Etapas>>(etapasNiveles,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("etapas/lecciones/{idLeccion}")
    public ResponseEntity<Lecciones> obtenerLeccionController(@PathVariable("idLeccion") int idLeccion) {
        try {
            Lecciones leccion = new LeccionesService().readLeccion(idLeccion);
            if (leccion == null) {
                throw new Exception("No se pudo obtener la información de la lección");
            }
            return new ResponseEntity<>(leccion, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((Lecciones) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("preguntas/nivel/{idNivel}")
    public ResponseEntity<List<PreguntasDTO>> obtenerPreguntasNivel(@PathVariable("idNivel") int idNivel) {
        try {
            List<PreguntasDTO> listaPreguntasNivel = new JuegoService().obtenerPreguntas(idNivel);
            return new ResponseEntity<>(listaPreguntasNivel, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("exp/{idUsuario}")
    public ResponseEntity<String> actualizarExpController(@PathVariable("idUsuario") int idUsuario) {
        try {
            int resultado = new JuegoService().actualizarExpUsuario(idUsuario);
            if(resultado == 0) {throw new Exception("no se pudo actualizad");}
            return new ResponseEntity<String>("usuario actualizado exitosamente", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
