package br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @NotBlank
    private String userName;

    @Email
    @NotNull
    private String email;

    @NotBlank
    private String userPassword;
}
