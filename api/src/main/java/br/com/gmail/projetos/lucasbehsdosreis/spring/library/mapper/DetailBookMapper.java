package br.com.gmail.projetos.lucasbehsdosreis.spring.library.mapper;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.controller.response.DetailBookResponse;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Book;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.domain.User;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class DetailBookMapper {
    public static DetailBookResponse toResponse(Book book) {

        if (isNull(book)) {
            return DetailBookResponse.builder().build();
        }

        return DetailBookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .status(book.getStatus())
                .inclusionDate(book.getInclusionDate())
                .devolutionDate(book.getDevolutionDate())
                .responsibleId(getResponsibleId(book.getResponsible()))
                .build();
    }

    private static Long getResponsibleId(User responsible) {
        return nonNull(responsible) ? responsible.getId() : null;
    }
}
