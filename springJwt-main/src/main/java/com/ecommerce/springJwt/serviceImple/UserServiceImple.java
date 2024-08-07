package com.ecommerce.springJwt.serviceImple;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.springJwt.dto.UserDto;
import com.ecommerce.springJwt.model.Product;
import com.ecommerce.springJwt.model.User;
import com.ecommerce.springJwt.repository.UserRepository;
import com.ecommerce.springJwt.service.UserService;

@Service
public class UserServiceImple implements UserService {
    private final UserRepository userRepository;

    public UserServiceImple(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> findallUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(User::getUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getByUserId(Long UserId) {
        Optional<User> optionalUser = userRepository.findById(UserId);
        if (optionalUser.isPresent()) {

            return optionalUser.get().getUserDto();
        }
        return null;
    }

}
