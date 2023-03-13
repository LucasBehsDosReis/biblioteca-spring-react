package lucasbehsdosreisprojeto.bibliotecaspring.security.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @NotBlank
    private String name;

    @Email
    @NotNull
    private String email;

    @NotBlank
    private String password;
}
