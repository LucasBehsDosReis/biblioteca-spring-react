package lucasbehsdosreisprojeto.bibliotecaspring.security.service;

import lucasbehsdosreisprojeto.bibliotecaspring.security.controller.request.UserRequest;
import lucasbehsdosreisprojeto.bibliotecaspring.security.controller.response.UserResponse;
import lucasbehsdosreisprojeto.bibliotecaspring.security.domain.Permission;
import lucasbehsdosreisprojeto.bibliotecaspring.security.domain.User;
import lucasbehsdosreisprojeto.bibliotecaspring.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static lucasbehsdosreisprojeto.bibliotecaspring.security.domain.Function.USER;
import static lucasbehsdosreisprojeto.bibliotecaspring.security.mapper.UserMapper.toEntity;
import static lucasbehsdosreisprojeto.bibliotecaspring.security.mapper.UserMapper.toResponse;

@Service
public class IncludeUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse includeUser(UserRequest request) {

        User user = toEntity(request);
        user.setPassword(getEncryptedPassword(request.getPassword()));
        user.addPermission(getDefaultPermission());
        user.setActive(true);

        userRepository.save(user);

        return toResponse(user);
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
