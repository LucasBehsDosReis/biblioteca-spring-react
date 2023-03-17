package br.com.gmail.projetos.lucasbehsdosreis.spring.library.service;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.controller.request.EditBookRequest;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.controller.response.DetailBookResponse;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Book;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.factories.BookFactory;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.factories.SimpleFactory;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.repository.BookRepository;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.service.core.FindBookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EditBookServiceTest {

    @InjectMocks
    private EditBookService tested;

    @Mock
    private FindBookService findBookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    @DisplayName("Must edit book correctly")
    void mustEditBookCorrectly() {

        EditBookRequest request = new EditBookRequest();
        request.setTitle("New Title");

        Book book = BookFactory.getBook();
        Long bookId = book.getId();

        when(findBookService.byId(bookId)).thenReturn(book);

        DetailBookResponse response = tested.editBook(bookId, request);

        verify(findBookService).byId(bookId);
        verify(bookRepository).save(book);

        assertEquals(request.getTitle(), response.getTitle());
    }

    @Test
    @DisplayName("Must not edit book when it does not exist")
    void mustNotEditBookWhenItDoesNotExist() {

        EditBookRequest request = new EditBookRequest();
        request.setTitle("New Title");

        Long bookId = SimpleFactory.getRandomLong();

        doThrow(ResponseStatusException.class)
                .when(findBookService).byId(bookId);

        assertThrows(ResponseStatusException.class, () -> {
            tested.editBook(bookId, request);
        });

        verify(findBookService).byId(bookId);
        verify(bookRepository, never()).save(any());
    }
}
