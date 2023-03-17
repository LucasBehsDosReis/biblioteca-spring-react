package br.com.gmail.projetos.lucasbehsdosreis.spring.library.factories;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.domain.User;

public class UserFactory {

    public static User getUser() {
        User user = new User();
        user.setId(SimpleFactory.getRandomLong());
        user.setUserName("Test User");
        user.setEmail("test@test.com.br");
        return user;
    }
}
