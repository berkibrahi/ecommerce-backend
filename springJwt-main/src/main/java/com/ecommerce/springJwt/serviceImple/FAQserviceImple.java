package com.ecommerce.springJwt.serviceImple;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.springJwt.dto.FAQDto;
import com.ecommerce.springJwt.model.FAQ;
import com.ecommerce.springJwt.model.Product;
import com.ecommerce.springJwt.repository.FAQRepository;
import com.ecommerce.springJwt.repository.ProductRepo;
import com.ecommerce.springJwt.service.FAQservice;

@Service
public class FAQserviceImple implements FAQservice {
    private FAQRepository faqRepository;
    private ProductRepo productRepo;

    public FAQserviceImple(FAQRepository faqRepository, ProductRepo productRepo) {
        this.faqRepository = faqRepository;
        this.productRepo = productRepo;
    }

    @Override
    public FAQDto postFaq(Long productId, FAQDto faqDto) {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (optionalProduct.isPresent()) {
            FAQ faq = new FAQ();
            faq.setQuestion(faqDto.getQuestion());
            faq.setAnswer(faqDto.getAnswer());
            faq.setProduct(optionalProduct.get());

            return faqRepository.save(faq).getFaqDto();

        }
        return null;
    }

}
