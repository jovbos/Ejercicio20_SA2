package com.example.Ejercicio20_SA2.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileDataRepository extends JpaRepository<FileData, Integer> {
    List<FileData>  findByName(String name);
}
