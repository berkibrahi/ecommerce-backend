package com.ecommerce.springJwt.service;

import com.ecommerce.springJwt.dto.FAQDto;

public interface FAQservice {

    public FAQDto postFaq(Long productId, FAQDto faqDto);
}
