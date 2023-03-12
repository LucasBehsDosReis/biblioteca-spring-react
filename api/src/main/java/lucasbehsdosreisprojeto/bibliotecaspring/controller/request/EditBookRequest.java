package lucasbehsdosreisprojeto.bibliotecaspring.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lucasbehsdosreisprojeto.bibliotecaspring.domain.Status;

@Getter
@Setter
public class EditBookRequest {

    @NotBlank
    private String title;

    @NotNull
    private Status status;
}
