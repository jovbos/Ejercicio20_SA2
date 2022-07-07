package com.example.Ejercicio20_SA2.application;

import com.example.Ejercicio20_SA2.domain.FileData;
import com.example.Ejercicio20_SA2.domain.FileDataRepository;
import com.example.Ejercicio20_SA2.infracstructure.FileDataDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class FileService {

    @Autowired
    FileDataRepository fileDataRepository;

    @Autowired
    ModelMapper modelMapper;
    public FileDataDTO uploadFile(MultipartFile file, String extension) throws Exception {

        String fileExtension = "." + file.getOriginalFilename().split("\\.")[1];

        if (fileExtension.equals(extension)){
            file.transferTo(
                new File(
                    "C:\\Users\\joaquin.olmedo\\Documents\\Ejercicios_Bosonit\\Ejercicio20_SA2\\files\\" + file.getOriginalFilename()));
                FileData fileData = new FileData();
                fileData.setName(file.getOriginalFilename());
                fileData.setUpload_date(LocalDate.now(ZoneId.of("GMT+2")));
                fileData.setExtension(fileExtension);
                fileDataRepository.save(fileData);

                FileDataDTO fileDataDTO =modelMapper.map(fileData, FileDataDTO.class);
                fileDataDTO.setId(fileData.getId());
                return fileDataDTO;

        } else {throw new Exception("THIS FILE DOES NOT MATCH THE GIVEN EXTENSION");}
    }

    public ResponseEntity<Object> downloadFileById(Integer id) throws Exception {

        FileData fileData = fileDataRepository.findById(id).orElseThrow(() -> new Exception("FILE NOT FOUND"));
        String name = fileData.getName();

        String filename = "C:\\Users\\joaquin.olmedo\\Documents\\Ejercicios_Bosonit\\Ejercicio20_SA2\\files\\"+name;
        File file = new File(filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt")).body(resource);

        return responseEntity;
    }
    public ResponseEntity<Object> downloadFileByName(String name) throws Exception {

        String filename = "C:\\Users\\joaquin.olmedo\\Documents\\Ejercicios_Bosonit\\Ejercicio20_SA2\\files\\"+name;
        File file = new File(filename);

            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers)
                .contentLength(file.length())
                .body(resource);

        return responseEntity;
    }
}
