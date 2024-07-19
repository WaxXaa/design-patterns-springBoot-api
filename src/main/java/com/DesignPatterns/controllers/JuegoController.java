package com.DesignPatterns.controllers;

import com.DesignPatterns.models.Etapas;
import com.DesignPatterns.models.PreguntasDTO;
import com.DesignPatterns.services.JuegoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("preguntas/nivel/{idNivel}")
    public ResponseEntity<List<PreguntasDTO>> obtenerPreguntasNivel(@PathVariable("idNivel") int idNivel) {
        try {
            List<PreguntasDTO> listaPreguntasNivel = new JuegoService().obtenerPreguntas(idNivel);
            return new ResponseEntity<>(listaPreguntasNivel, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
