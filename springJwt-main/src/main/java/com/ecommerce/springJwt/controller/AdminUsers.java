package com.ecommerce.springJwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.springJwt.dto.UserDto;
import com.ecommerce.springJwt.model.Product;
import com.ecommerce.springJwt.repository.ProductRepo;
import com.ecommerce.springJwt.repository.UserRepository;
import com.ecommerce.springJwt.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminUsers {

    @Autowired

    private UserService userService;

    public AdminUsers(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findallUser());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getByUserId(userId));
    }

    @GetMapping("/adminuser/both")
    public ResponseEntity<Object> bothAdminaAndUsersApi() {
        return ResponseEntity.ok("Both Admin and Users Can  access the api");
    }

    /**
     * You can use this to get the details(name,email,role,ip, e.t.c) of user
     * accessing the service
     */

}
