package com.example.Ejercicio20_SA2.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.File;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class FileData {

    @Id
    @GeneratedValue
    @Column(name="Id")
    private Integer id;

    @Column(name="Name")
    private String name;

    @Column(name="Upload_date")
    private LocalDate upload_date;

    @Column(name="Extension")
    private String extension;


}
