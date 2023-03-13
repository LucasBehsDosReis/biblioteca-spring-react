package br.com.gmail.projetos.lucasbehsdosreis.spring.library.mapper;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.controller.request.BookRequest;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.controller.response.BookResponse;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Book;

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
