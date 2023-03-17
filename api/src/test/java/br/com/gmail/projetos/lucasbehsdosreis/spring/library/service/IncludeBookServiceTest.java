package br.com.gmail.projetos.lucasbehsdosreis.spring.library.service;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.controller.request.BookRequest;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Book;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.factories.SimpleFactory;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.repository.BookRepository;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.service.core.NowService;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.service.core.ValidateBookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Status.ACTIVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IncludeBookServiceTest {

    @InjectMocks
    private IncludeBookService tested;

    @Mock
    private ValidateBookService validateBookService;

    @Mock
    private NowService nowService;

    @Mock
    private BookRepository bookRepository;

    @Captor
    private ArgumentCaptor<Book> bookArgumentCaptor;

    @Test
    @DisplayName("Must include new book as active and with inclusion date")
    void mustIncludeNewBookAsActiveAndWithInclusionDate() {

        BookRequest request = new BookRequest();

        request.setTitle("Test Title");

        String bookTitle = request.getTitle();

        LocalDate today = SimpleFactory.getNowDate();

        when(nowService.getDate()).thenReturn(today);

        tested.includeBook(request);

        verify(validateBookService).validateBookByTitle(bookTitle);
        verify(bookRepository).save(bookArgumentCaptor.capture());

        Book book = bookArgumentCaptor.getValue();

        assertEquals(ACTIVE, book.getStatus());
        assertEquals(today, book.getInclusionDate());
    }

    @Test
    @DisplayName("Must not include book when there is another book with da same name")
    void mustNotIncludeBookWhenThereIsAnotherBookWithTheSameName() {

        BookRequest request = new BookRequest();

        request.setTitle("Test Title");

        String bookTitle = request.getTitle();

        doThrow(ResponseStatusException.class)
                .when(validateBookService).validateBookByTitle(bookTitle);

        assertThrows(ResponseStatusException.class, () -> {
            tested.includeBook(request);
        });

        verify(bookRepository, never()).save(bookArgumentCaptor.capture());
    }
}
