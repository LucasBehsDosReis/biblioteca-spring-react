package lucasbehsdosreisprojeto.bibliotecaspring.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lucasbehsdosreisprojeto.bibliotecaspring.domain.Status;

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
