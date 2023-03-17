package br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
