package br.com.gmail.projetos.lucasbehsdosreis.spring.library.controller.response;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(NON_NULL)
public class BookResponse {

    private Long id;
    private String title;
    private Status status;
}
