package com.example.controller;

import com.example.service.EmailSenderService;
import com.example.service.EmailSenderServiceHTML;
import com.example.service.FlatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.repository.FlatRepository;
import com.example.model.Flat;
import com.example.exception.ResourceNotFoundException;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("http://localhost:4200/")
public class FlatController {

    @Autowired
    private EmailSenderService senderService;

    @Autowired
    private EmailSenderServiceHTML senderServiceHTML;

    Logger logger = LoggerFactory.getLogger(FlatController.class);

    @Autowired
    private FlatRepository flatRepository ;

    @Autowired
    private FlatService flatService;

    //get all flats
    @GetMapping("/flats")
    public List<Flat> getAllFlats(){
        return flatService.getAllFlats();
    }

    //create daire rest api tanimi
    @PostMapping("/flats")
    public ResponseEntity<Flat> createFlat(@RequestBody Flat flat) {
        Flat createdFlat = flatService.createFlat(flat);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFlat);
    }

    //get flat by id
    @GetMapping("/flats/{id}")
    //response entity classını kullanarak dinamik veri dönüşüne izin veriyoruz
    public ResponseEntity<Flat> getFlatId(@PathVariable Long id){
        ResponseEntity<Flat> response = flatService.getFlatById(id);
        return response;
    }


    @CrossOrigin(exposedHeaders = "X-Total-Pages")
    @GetMapping("/flats-pagination/{page}")
    public ResponseEntity<List<Flat>> getFlatsByPage(@PathVariable int page) {
        return flatService.getFlatsByPage(page);
    }


    //update flat restapi
    @PutMapping("flats/{id}")
    public ResponseEntity<Flat> updateflat(@PathVariable Long id,@RequestBody Flat flatDetails){
        return flatService.updateFlat(id, flatDetails);
    }


    //delete flat
    @DeleteMapping("/flats/{id}")
    public ResponseEntity<Flat> deleteFlat(@PathVariable Long id) {
        return flatService.deleteFlat(id);
    }

}
