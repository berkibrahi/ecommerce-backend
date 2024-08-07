package com.ecommerce.springJwt.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.ecommerce.springJwt.dto.FAQDto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class FAQ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String question;
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)

    private Product product;

    public FAQDto getFaqDto() {
        FAQDto faqDto = new FAQDto();
        faqDto.setId(id);
        ;
        faqDto.setAnswer(answer);
        faqDto.setQuestion(question);
        faqDto.setProductId(product.getId());

        return faqDto;
    }

}
