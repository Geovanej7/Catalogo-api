package com.catalogo.catalogo_api.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {
    public static boolean UploadImagem(MultipartFile image){

        boolean successUpload = false;

        if(!image.isEmpty()){
            String name = image.getOriginalFilename();

            try {
                // creating directory;
                String folder = "C:\\Users\\Geovane\\Documents\\catalogo\\Catalogo-api\\src\\main\\resources\\static\\uploaded-images";
                File dir = new File(folder);
                if(!dir.exists()){
                    dir.mkdirs();
                }

                // creating file in directory;
                File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

                stream.write(image.getBytes());
                stream.close();

                System.out.println("stored in: " + serverFile.getAbsolutePath());
                System.out.println("file upload: " + name + " successful");

                successUpload = true;

            } catch (Exception e) {
                System.out.println("error loading file: " + name + " => " + e.getMessage());
            }
        } else {
            System.out.println("empty file");
        }

        return successUpload;
    }
}
