package br.com.gmail.projetos.lucasbehsdosreis.spring.library.service.core;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Book;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.factories.BookFactory;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindBookServiceTest {

    @InjectMocks
    private FindBookService tested;

    @Mock
    private BookRepository bookRepository;

    @Test
    @DisplayName("Must find book by id")
    void mustFindBookById() {
        Book book = BookFactory.getBook();
        Long bookId = book.getId();

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Book response = tested.byId(bookId);

        verify(bookRepository).findById(bookId);

        assertEquals(book, response);
    }

    @Test
    @DisplayName("Must throw error when book not found")
    void mustThrowErrorWhenBookNotFound() {

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class, () -> tested.byId(1l));

        assertEquals("Book not found!", exception.getReason());
    }
}
