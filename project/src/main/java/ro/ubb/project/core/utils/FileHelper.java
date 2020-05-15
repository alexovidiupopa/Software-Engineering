package ro.ubb.project.core.utils;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileHelper {

    public static String storeFile(MultipartFile file, String location) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get(location.substring(1)).toAbsolutePath().normalize();
        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("!@#%^&*()")) {
                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = path.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return targetLocation.toString();
        } catch (IOException ex) {
            System.out.println(ex);
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public static Resource loadFileAsResource(String path) {
        try {
            Path filePath = Paths.get(path.substring(1)).toAbsolutePath().normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found " + path);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("File not found " + path, ex);
        }
    }

    public static File loadAsFile(String path) {
        Path filePath = Paths.get(path.substring(1)).toAbsolutePath().normalize();
        return filePath.toFile();
    }
}
