package br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.service;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.controller.request.UserRequest;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.controller.response.UserResponse;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.mapper.UserMapper;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.domain.Permission;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.domain.User;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.domain.Function.USER;

@Service
public class IncludeUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse includeUser(UserRequest request) {

        User user = UserMapper.toEntity(request);
        user.setUserPassword(getEncryptedPassword(request.getUserPassword()));
        user.addPermission(getDefaultPermission());
        user.setActive(true);

        userRepository.save(user);

        return UserMapper.toResponse(user);
    }

    private String getEncryptedPassword(String openPassword) {
        return passwordEncoder.encode(openPassword);
    }

    private Permission getDefaultPermission() {
        Permission permission = new Permission();
        permission.setFunction(USER);
        return permission;
    }
}
