package lucasbehsdosreisprojeto.bibliotecaspring.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lucasbehsdosreisprojeto.bibliotecaspring.domain.Status;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(NON_NULL)
public class DetailBookResponse {

    private Long id;
    private String title;
    private Status status;
    private LocalDate inclusionDate;
    private LocalDate devolutionDate;
}
