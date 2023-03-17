package br.com.gmail.projetos.lucasbehsdosreis.spring.library.factories;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Book;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.domain.User;

import java.time.LocalDate;

import static br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Status.ACTIVE;

public class BookFactory {

    public static Book getBook() {
        return Book.builder()
                .id(SimpleFactory.getRandomLong())
                .title("Test Book")
                .status(ACTIVE)
                .inclusionDate(SimpleFactory.getNowDate())
                .build();

    }

    public static Book getRentedBook(User user, LocalDate devolutionDate) {
        return Book.builder()
                .id(SimpleFactory.getRandomLong())
                .title("Test Book")
                .status(ACTIVE)
                .inclusionDate(SimpleFactory.getNowDate())
                .responsible(user)
                .devolutionDate(devolutionDate)
                .build();

    }
}
