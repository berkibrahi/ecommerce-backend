package com.ecommerce.springJwt.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.ecommerce.springJwt.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long price;
    private Long discount;

    private Long quantity;
    @Lob
    private String description;

    @Lob
    @Column(columnDefinition = "longblob")
    private String img;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;

    public ProductDto getDto() {
        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        productDto.setName(name);
        productDto.setDescription(description);

        productDto.setPrice(price);
        productDto.setByteImg(img);
        productDto.setDiscount(discount);
        productDto.setQuantity(quantity);
        productDto.setCategoryId(category.getId());
        return productDto;

    }
}
