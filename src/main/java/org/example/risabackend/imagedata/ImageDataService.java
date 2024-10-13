package org.example.risabackend.imagedata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageDataService {
    @Autowired
    private ImageDataRepository imageDataRepository;

    public ImageData uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = new ImageData(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        System.out.println(imageData);
        imageDataRepository.save(imageData);
        return imageData;
    }
}
