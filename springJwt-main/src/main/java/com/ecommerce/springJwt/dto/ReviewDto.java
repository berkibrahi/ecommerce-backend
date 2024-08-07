package com.ecommerce.springJwt.dto;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.ecommerce.springJwt.model.Product;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ReviewDto {

    private Long id;

    private Long rating;

    @Lob
    private String description;

    private String ReturnedImg;

    private Long userId;

    private Long productId;

    private String userName;
}
