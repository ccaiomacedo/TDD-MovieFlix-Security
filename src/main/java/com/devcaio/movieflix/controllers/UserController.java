package com.devcaio.movieflix.controllers;

import com.devcaio.movieflix.dto.UserDTO;
import com.devcaio.movieflix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/profile")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping()
    public ResponseEntity<UserDTO> findProfileSelf() {
        UserDTO dto = service.findProfileSelf();
        return ResponseEntity.ok(dto);
    }
}
