package com.example.demo.Services.Impl;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.Services.ImageService;
import com.example.demo.helper.Apponstants;

@Service
public class ImageServiceImple implements ImageService{


    private Cloudinary cloudnary;

    

    public ImageServiceImple(Cloudinary cloudnary) {
        this.cloudnary = cloudnary;
    }



    @Override
    public String uploadImage(MultipartFile picture,String fileName) {
      try {
        byte[] data=new byte[picture.getInputStream().available()];
        picture.getInputStream().read(data);
        cloudnary.uploader().upload(data, ObjectUtils.asMap(
            "public_id",fileName
        ));

        return getUrlFromPublicId(fileName);

    } catch (IOException e) {
        return null;  
    }
    }



    @Override
    public String getUrlFromPublicId(String publicId) {
        return cloudnary.url().transformation(
            new Transformation<>().width(Apponstants.CONTACT_IMAGE_WIDTH).height(Apponstants.CONTACT_IMAGE_HEIGHT).crop(Apponstants.CONTACT_IMAGE_CROP)
        ).generate(publicId);
    }

}
