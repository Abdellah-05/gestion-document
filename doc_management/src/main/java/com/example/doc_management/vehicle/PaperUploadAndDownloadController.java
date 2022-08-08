package com.example.doc_management.vehicle;

import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
public class PaperUploadAndDownloadController {

    @PostMapping("/upload")
    public String uploadFile (MultipartFile file) {
        String distenationFile = ".\\uploads\\" + file.getOriginalFilename();
        try{
            Files.copy(file.getInputStream(),
                    Paths.get(distenationFile),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "uploaded";
    }

    @GetMapping("/download/{fileName}")
    public UrlResource downloadFile (@PathVariable String fileName) {
        Path pathToFile = Paths.get(".\\uploads\\" + fileName);
        UrlResource resource = null;
        try{
            resource = new UrlResource(pathToFile.toUri());

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return resource;
    }
}
