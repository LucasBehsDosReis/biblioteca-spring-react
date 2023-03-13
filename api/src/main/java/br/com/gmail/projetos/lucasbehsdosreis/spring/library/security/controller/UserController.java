package br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.controller;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.controller.request.UserRequest;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.controller.response.UserResponse;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.service.IncludeUserService;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.service.FindUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IncludeUserService includeUserService;

    @Autowired
    private FindUserService findUserService;

    @PostMapping
    public UserResponse includeUser(@RequestBody UserRequest request) {
        return includeUserService.includeUser(request);
    }

    @GetMapping("/me")
    public UserResponse findUser() {
        return findUserService.findUser();
    }
}
