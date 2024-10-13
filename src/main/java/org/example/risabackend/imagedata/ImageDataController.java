package org.example.risabackend.imagedata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/imagedata")
public class ImageDataController {
    @Autowired
    private ImageDataService imageDataService;

    public ImageDataController(ImageDataService imageDataService) {
        this.imageDataService = imageDataService;
    }

    @PostMapping
    public void uploadImage(@RequestBody MultipartFile file) throws IOException {
        ImageData imageData = imageDataService.uploadImage(file);
        System.out.println(imageData.toString());
//        return ResponseEntity.status(HttpStatus.OK).body(imageData);
    }
}
