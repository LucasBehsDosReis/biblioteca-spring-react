package br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.controller.response;

import lombok.*;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.domain.Function;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse {

    private Long id;
    private String userName;
    private String email;
    private List<Function> permissions;
}
