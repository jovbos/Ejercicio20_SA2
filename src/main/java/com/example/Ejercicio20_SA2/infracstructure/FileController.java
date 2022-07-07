package com.example.Ejercicio20_SA2.infracstructure;

import com.example.Ejercicio20_SA2.application.FileService;
import com.example.Ejercicio20_SA2.infracstructure.FileDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping("/upload/{extension}")
    public FileDataDTO uploadFile(@RequestParam("file")MultipartFile file, @PathVariable("extension") String extension) throws Exception {

        return fileService.uploadFile(file, extension);
    }

    @GetMapping("/downloadById")
    public ResponseEntity<Object> downloadFileById(Integer id) throws Exception
    {

        return fileService.downloadFileById(id);
    }

    @GetMapping("/downloadByName")
    public ResponseEntity<Object> downloadFileByName(String name) throws Exception
    {

        return fileService.downloadFileByName(name);
    }

}