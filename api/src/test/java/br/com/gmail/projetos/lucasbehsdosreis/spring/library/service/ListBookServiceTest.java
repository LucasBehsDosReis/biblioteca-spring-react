package br.com.gmail.projetos.lucasbehsdosreis.spring.library.service;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.controller.response.BookResponse;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Book;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.factories.BookFactory;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Status.ACTIVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListBookServiceTest {

    @InjectMocks
    private ListBookService tested;

    @Mock
    private BookRepository bookRepository;

    @Test
    @DisplayName("Must find all active books and return paginated response")
    void mustFindAllActiveBooksAndReturnPaginatedResponse() {

        Pageable pageable = PageRequest.of(0, 5);

        List<Book> books = List.of(
                BookFactory.getBook(),
                BookFactory.getBook(),
                BookFactory.getBook()
        );

        Page<Book> pageableBooks = new PageImpl<>(books);

        when(bookRepository.findAllByStatus(ACTIVE, pageable)).thenReturn(pageableBooks);

        Page<BookResponse> responses = tested.listBookPageable(pageable);

        verify(bookRepository).findAllByStatus(ACTIVE, pageable);
        assertEquals(books.size(), responses.getSize());
        assertEquals(books.get(0).getId(), responses.getContent().get(0).getId());
        assertEquals(books.get(1).getId(), responses.getContent().get(1).getId());
        assertEquals(books.get(2).getId(), responses.getContent().get(2).getId());
    }

    @Test
    @DisplayName("Must return empty list when find no books")
    void mustReturnEmptyListWhenFindNoBooks() {

        Pageable pageable = PageRequest.of(0, 5);

        Page<Book> pageableBooks = new PageImpl<>(new ArrayList<>());

        when(bookRepository.findAllByStatus(ACTIVE, pageable)).thenReturn(pageableBooks);

        Page<BookResponse> responses = tested.listBookPageable(pageable);

        assertNotNull(responses);
        assertEquals(0, responses.getSize());
    }
}
