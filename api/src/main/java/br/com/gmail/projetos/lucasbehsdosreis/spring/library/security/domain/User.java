package br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Table(name = "account")
@EqualsAndHashCode(of = "id") @ToString(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(nullable = false, name = "account_name")
    private String userName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, name = "account_password")
    private String userPassword;

    @Column(nullable = false)
     private boolean active;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Permission> permissions = new ArrayList<>();


    public void addPermission(Permission permission) {
        this.permissions.add(permission);
        permission.setUser(this);
    }
}
