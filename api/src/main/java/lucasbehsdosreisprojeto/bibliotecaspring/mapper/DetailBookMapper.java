package lucasbehsdosreisprojeto.bibliotecaspring.mapper;

import lucasbehsdosreisprojeto.bibliotecaspring.controller.response.DetailBookResponse;
import lucasbehsdosreisprojeto.bibliotecaspring.domain.Book;

import static java.util.Objects.isNull;

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
                .build();
    }
}
