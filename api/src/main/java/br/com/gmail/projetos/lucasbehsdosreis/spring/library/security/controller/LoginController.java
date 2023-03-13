package br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.controller;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.service.UserAuthenticationService;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.controller.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @PostMapping
    public UserResponse login() {
        return userAuthenticationService.getResponse();
    }
}
