package br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.config;

import lombok.Getter;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.domain.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserSecurity implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    public UserSecurity(User user) {

        this.id = user.getId();
        this.username = user.getEmail();
        this.password = user.getUserPassword();

        this.accountNonExpired = user.isActive();
        this.accountNonLocked = user.isActive();
        this.credentialsNonExpired = user.isActive();
        this.enabled = user.isActive();

        this.authorities = user.getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getFunction().getRole()))
                .collect(Collectors.toList());
    }
}
