package com.example.Ejercicio20_SA2.infracstructure;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FileDataDTO {

    private Integer id;

    private String name;

    private LocalDate upload_date;

    private String extension;
}
