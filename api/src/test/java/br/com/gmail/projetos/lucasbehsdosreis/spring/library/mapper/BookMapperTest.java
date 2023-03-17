package br.com.gmail.projetos.lucasbehsdosreis.spring.library.mapper;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.controller.response.BookResponse;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Book;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.factories.BookFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BookMapperTest {

    @Test
    @DisplayName("Must return complete response when complete request!")
    void mustReturnCompleteResponse() {

        Book book = BookFactory.getBook();

        BookResponse response = BookMapper.toResponse(book);

        assertEquals(book.getId(), response.getId());
        assertEquals(book.getTitle(), response.getTitle());
        assertEquals(book.getStatus(), response.getStatus());
    }
}
