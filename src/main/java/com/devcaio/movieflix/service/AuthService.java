package com.devcaio.movieflix.service;

import com.devcaio.movieflix.entities.User;
import com.devcaio.movieflix.repositories.UserRepository;
import com.devcaio.movieflix.service.exceptions.ForbiddenException;
import com.devcaio.movieflix.service.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public User authenticated() {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return repository.findByEmail(username);
        } catch (Exception e) {
            throw new UnauthorizedException("Invalid user");
        }
    }

    public void validateSelfOrAdmin(Long userId) {
        User user = authenticated();
        if (!user.getId().equals(userId)) {
            throw new ForbiddenException("Acess denied");
        }
    }
}
