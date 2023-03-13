package lucasbehsdosreisprojeto.bibliotecaspring.security.mapper;

import lucasbehsdosreisprojeto.bibliotecaspring.security.controller.request.UserRequest;
import lucasbehsdosreisprojeto.bibliotecaspring.security.controller.response.UserResponse;
import lucasbehsdosreisprojeto.bibliotecaspring.security.domain.User;

public class UserMapper {

    public static User toEntity(UserRequest request) {
        User entity = new User();
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        return entity;
    }

    public static UserResponse toResponse(User entity) {
        UserResponse response = new UserResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setEmail(entity.getEmail());
        return response;
    }
}
