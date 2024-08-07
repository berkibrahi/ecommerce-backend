package com.ecommerce.springJwt.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Data;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductDto {

    private Long id;

    private String name;
    private Long price;

    private String description;

    private String byteImg;
    private Long categoryId;
    private Long discount;
    private Long quantity;

}
