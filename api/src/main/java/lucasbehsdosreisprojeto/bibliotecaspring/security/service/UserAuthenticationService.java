package lucasbehsdosreisprojeto.bibliotecaspring.security.service;

import lucasbehsdosreisprojeto.bibliotecaspring.security.config.UserSecurity;
import lucasbehsdosreisprojeto.bibliotecaspring.security.controller.response.UserResponse;
import lucasbehsdosreisprojeto.bibliotecaspring.security.domain.User;
import lucasbehsdosreisprojeto.bibliotecaspring.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static lucasbehsdosreisprojeto.bibliotecaspring.security.mapper.UserMapper.toResponse;

@Service
public class UserAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public Long getId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof UserSecurity) {
            return ((UserSecurity) authentication.getPrincipal()).getId();
        }

        return null;
    }

    public User get() {
        Long id = getId();

        if(isNull(id)) {
            return null;
        }

        return userRepository.findById(getId()).orElse(null);
    }

    public UserResponse getResponse() {
        User entity = get();
        return nonNull(entity) ? toResponse(entity) : new UserResponse();
    }
}
