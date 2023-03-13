package br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.mapper;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.controller.request.UserRequest;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.controller.response.UserResponse;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.domain.Function;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.domain.Permission;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.domain.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class UserMapper {

    public static User toEntity(UserRequest request) {
        User entity = new User();
        entity.setUserName(request.getUserName());
        entity.setEmail(request.getEmail());
        return entity;
    }

    public static UserResponse toResponse(User entity) {
        return UserResponse.builder()
                .id(entity.getId())
                .userName(entity.getUserName())
                .email(entity.getEmail())
                .permissions(buildPermissionsResponse(entity.getPermissions()))
                .build();
    }

    private static List<Function> buildPermissionsResponse(List<Permission> permissions) {
        return permissions.stream()
                .map(Permission::getFunction)
                .collect(toList());
    }
}
