package br.com.gmail.projetos.lucasbehsdosreis.spring.library.service;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Book;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.factories.BookFactory;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.factories.SimpleFactory;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.factories.UserFactory;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.repository.BookRepository;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.domain.User;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.service.core.FindBookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReturnBookServiceTest {

    @InjectMocks
    private ReturnBookService tested;

    @Mock
    private FindBookService findBookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    @DisplayName("Must return rented book")
    void mustReturnRentedBook() {

        User user = UserFactory.getUser();
        LocalDate devolutionDate = SimpleFactory.getDevolutionDate(now());

        Book book = BookFactory.getRentedBook(user, devolutionDate);
        Long bookId = book.getId();

        when(findBookService.byId(bookId)).thenReturn(book);

        tested.returnBook(bookId);

        verify(findBookService).byId(bookId);
        verify(bookRepository).save(book);

        assertNull(book.getResponsible());
        assertNull(book.getDevolutionDate());
    }

    @Test
    @DisplayName("Must not return book when it has not been rented")
    void mustNotReturnBookWhenItHasNotBeenRented() {

        Book book = BookFactory.getBook();
        Long bookId = book.getId();

        when(findBookService.byId(bookId)).thenReturn(book);

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class, () -> tested.returnBook(bookId));

        verify(findBookService).byId(bookId);
        verify(bookRepository, never()).save(book);

        assertEquals("The book has not been rented!", exception.getReason());
    }

    @Test
    @DisplayName("Must not return book when it does not exist")
    void mustNotReturnBookWhenItDoesNotExist() {

        Long bookId = SimpleFactory.getRandomLong();

        doThrow(ResponseStatusException.class).when(findBookService).byId(bookId);

        assertThrows(ResponseStatusException.class, () -> tested.returnBook(bookId));

        verify(findBookService).byId(bookId);
        verify(bookRepository, never()).save(any());
    }
}
