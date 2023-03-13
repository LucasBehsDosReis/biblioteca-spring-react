package lucasbehsdosreisprojeto.bibliotecaspring.security.controller;

import lucasbehsdosreisprojeto.bibliotecaspring.security.controller.request.UserRequest;
import lucasbehsdosreisprojeto.bibliotecaspring.security.controller.response.UserResponse;
import lucasbehsdosreisprojeto.bibliotecaspring.security.service.IncludeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IncludeUserService includeUserService;

    @PostMapping
    public UserResponse includeUser(@RequestBody UserRequest request) {
        return includeUserService.includeUser(request);
    }
}
