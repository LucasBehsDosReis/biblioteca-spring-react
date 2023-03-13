package br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.service;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.controller.response.UserResponse;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.domain.User;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindUserService {

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    public UserResponse findUser() {
        User authenticatedUser = userAuthenticationService.get();
        return UserMapper.toResponse(authenticatedUser);
    }
}
