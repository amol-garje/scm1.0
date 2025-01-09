package com.example.demo.validaters;


import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class FileValidator implements ConstraintValidator<validFile,MultipartFile>{
   
   
    private static final long MAX_SIZE_FILE_SIZE=1024 * 1024 * 2;

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {   
        
        if(file==null || file.isEmpty()){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File Can not be Empty").addConstraintViolation();   
            return false;
        }

        if(file.getSize()>MAX_SIZE_FILE_SIZE){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File Size should be 2MB").addConstraintViolation();   
            return false;
        }

    //    try {
    //     BufferedImage bimage = ImageIO.read(file.getInputStream());
     
    //    } catch (IOException e) {
    //          e.printStackTrace();
    //    }

      return true;

    }
}
