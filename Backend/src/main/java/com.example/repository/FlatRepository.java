package com.example.repository;

import com.example.model.Flat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlatRepository extends JpaRepository <Flat,Long> {


    Page<Flat> findAll(Pageable pageable);
}
