package lucasbehsdosreisprojeto.bibliotecaspring.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {

    @NotBlank
    private String title;
}
