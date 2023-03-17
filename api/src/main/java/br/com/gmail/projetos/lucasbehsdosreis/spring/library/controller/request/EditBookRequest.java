package br.com.gmail.projetos.lucasbehsdosreis.spring.library.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EditBookRequest {

    @NotBlank
    private String title;
}
