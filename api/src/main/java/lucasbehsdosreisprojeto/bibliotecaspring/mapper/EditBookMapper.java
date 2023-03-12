package lucasbehsdosreisprojeto.bibliotecaspring.mapper;

import lucasbehsdosreisprojeto.bibliotecaspring.controller.request.EditBookRequest;
import lucasbehsdosreisprojeto.bibliotecaspring.domain.Book;

public class EditBookMapper {
    public static Book toEntity(EditBookRequest request) {
        return Book.builder()
                .title(request.getTitle())
                .status(request.getStatus())
                .build();
    }
}
