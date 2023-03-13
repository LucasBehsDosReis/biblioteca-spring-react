package lucasbehsdosreisprojeto.bibliotecaspring.security.domain;


import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id") @ToString(of = "id")
public class Permission {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated(STRING)
    private Function function;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
