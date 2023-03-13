package br.com.gmail.projetos.lucasbehsdosreis.spring.library.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditBookRequest {

    @NotBlank
    private String title;
}
