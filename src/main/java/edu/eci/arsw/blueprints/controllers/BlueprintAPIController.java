/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping("/blueprints")
public class BlueprintAPIController {
    
    
    private static final Logger logger = Logger.getLogger(BlueprintAPIController.class.getName());

    @Autowired
    BlueprintsServices blueprintServices;

    @GetMapping
    public ResponseEntity<?> getAllBlueprints() {
        try {
            Set<Blueprint> all = blueprintServices.getAllBlueprints();
            return new ResponseEntity<>(all, HttpStatus.ACCEPTED);
        } catch (BlueprintPersistenceException ex) {
            logger.log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error obteniendo blueprints", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{author}")
    public ResponseEntity<?> getBlueprintsByAuthor(@PathVariable("author") String author) {
        try {
            Set<Blueprint> bps = blueprintServices.getBlueprintsByAuthor(author);
            return new ResponseEntity<>(bps, HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            logger.log(Level.WARNING, null, ex);
            return new ResponseEntity<>("Author not found: " + author, HttpStatus.NOT_FOUND);
        } catch (BlueprintPersistenceException ex) {
            logger.log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{author}/{bpname}")
    public ResponseEntity<?> getBlueprintByAuthorAndName(@PathVariable("author") String author,
                                                        @PathVariable("bpname") String bpname) {
        try {
            Blueprint bp = blueprintServices.getBlueprint(author, bpname);
            return new ResponseEntity<>(bp, HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            logger.log(Level.WARNING, null, ex);
            return new ResponseEntity<>("Blueprint not found: " + author + " - " + bpname, HttpStatus.NOT_FOUND);
        } catch (BlueprintPersistenceException ex) {
            logger.log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> addNewBlueprint(@RequestBody Blueprint bp) {
        try {
            blueprintServices.addNewBlueprint(bp);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (BlueprintPersistenceException ex) {
            logger.log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error registrando el plano", HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/{author}/{bpname}")
    public ResponseEntity<?> updateBlueprint(@PathVariable("author") String author,
                                            @PathVariable("bpname") String bpname,
                                            @RequestBody Blueprint updatedBp) {
        try {
            blueprintServices.updateBlueprint(author, bpname, updatedBp);
            return new ResponseEntity<>(HttpStatus.ACCEPTED); 
        } catch (BlueprintNotFoundException ex) {
            logger.log(Level.WARNING, null, ex);
            return new ResponseEntity<>("Blueprint not found", HttpStatus.NOT_FOUND);
        } catch (BlueprintPersistenceException ex) {
            logger.log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error actualizando el plano", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

