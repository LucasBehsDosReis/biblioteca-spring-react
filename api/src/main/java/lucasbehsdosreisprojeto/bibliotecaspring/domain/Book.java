package lucasbehsdosreisprojeto.bibliotecaspring.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Book {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Enumerated(STRING)
    @Column(nullable = false)
    private Status status;

    private LocalDate inclusionDate;

    private LocalDate devolutionDate;
}
