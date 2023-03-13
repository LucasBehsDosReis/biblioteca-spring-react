package br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.config;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FindUserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return userRepository.findByEmail(email)
                .map(UserSecurity::new)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials!"));
    }
}
