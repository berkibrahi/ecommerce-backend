package com.ecommerce.springJwt.service;

import java.util.List;

import com.ecommerce.springJwt.dto.UserDto;

public interface UserService {

    public List<UserDto> findallUser();

    public UserDto getByUserId(Long UserId);
}
