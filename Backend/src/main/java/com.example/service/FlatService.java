package com.example.service;
import com.example.model.Flat;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FlatService {


    List<Flat> getAllFlats();
    ResponseEntity<Flat> getFlatById(Long id);
    Flat createFlat(Flat flat);
    ResponseEntity<List<Flat>> getFlatsByPage(int page); // Yeni yöntem imzası
    ResponseEntity<Flat> updateFlat(Long id, Flat flatDetails);
    ResponseEntity<Flat> deleteFlat(Long id);

}
