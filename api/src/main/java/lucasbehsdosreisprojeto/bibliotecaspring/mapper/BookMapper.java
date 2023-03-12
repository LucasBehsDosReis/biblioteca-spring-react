package lucasbehsdosreisprojeto.bibliotecaspring.mapper;

import lucasbehsdosreisprojeto.bibliotecaspring.controller.request.BookRequest;
import lucasbehsdosreisprojeto.bibliotecaspring.controller.response.BookResponse;
import lucasbehsdosreisprojeto.bibliotecaspring.domain.Book;

public class BookMapper {

    public static Book toEntity(BookRequest request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        return book;
    }

    public static BookResponse toResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .status(book.getStatus())
                .build();
    }
}
