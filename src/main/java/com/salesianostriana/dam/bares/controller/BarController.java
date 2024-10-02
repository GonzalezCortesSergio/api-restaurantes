package com.salesianostriana.dam.bares.controller;

import com.salesianostriana.dam.bares.model.Bar;
import com.salesianostriana.dam.bares.model.Tag;
import com.salesianostriana.dam.bares.service.BarService;
import com.salesianostriana.dam.bares.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/place")
public class BarController {

    //Atributos

    private final BarService barService;
    private final TagService tagService;


    //Métodos

    @GetMapping
    public ResponseEntity<List<Bar>> listarTodos() {

        if (barService.findAll().isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<Bar>>(barService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bar> buscarPorId(@PathVariable Long id) {

        Optional<Bar> optionalBar = barService.findById(id);
        return optionalBar.map(bar -> new ResponseEntity<>(bar, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping
    public ResponseEntity<Bar> crear(@RequestBody Bar bar) {

        return new ResponseEntity<Bar>(barService.save(bar), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bar> editar(@PathVariable Long id, @RequestBody Bar bar){

        Bar editar = barService.edit(id, bar);
        if (editar == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(editar, HttpStatus.OK);
    }

    @PutMapping("/{id}/tag/add/")
    public ResponseEntity<Bar> agregarTag(@PathVariable Long id, @RequestBody Tag tag) {

        Bar bar = barService.agregarTag(id, tag);

        if(bar == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(bar, HttpStatus.OK);
    }

    @PutMapping("/{id}/tag/del/{tagId}")
    public ResponseEntity<Bar> borrarTag(@PathVariable("id") Long id, @PathVariable("tagId") Long tagId) {

        Bar bar = barService.eliminarTag(id, tagId);

        if(bar == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(bar, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarBar(@PathVariable Long id) {

        Optional<Bar> optionalBar = barService.findById(id);
        if(optionalBar.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        barService.delete(optionalBar.get());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
