package br.com.gmail.projetos.lucasbehsdosreis.spring.library.service;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.controller.response.DetailBookResponse;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Book;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.factories.BookFactory;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.factories.SimpleFactory;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.factories.UserFactory;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.repository.BookRepository;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.domain.User;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.service.UserAuthenticationService;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.service.core.BookReturnCalculationService;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.service.core.FindBookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RendBookServiceTest {

    @InjectMocks
    private RendBookService tested;

    @Mock
    private UserAuthenticationService userAuthenticationService;

    @Mock
    private FindBookService findBookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookReturnCalculationService bookReturnCalculationService;

    @Test
    @DisplayName("Must rent book correctly")
    void mustRendBookCorrectly(){

        User user = UserFactory.getUser();
        Long userId = user.getId();
        Book book = BookFactory.getBook();
        Long bookId = book.getId();
        LocalDate today = SimpleFactory.getNowDate();
        LocalDate devolutionDate = SimpleFactory.getDevolutionDate(today);

        when(findBookService.byId(bookId)).thenReturn(book);
        when(userAuthenticationService.get()).thenReturn(user);
        when(bookReturnCalculationService.calculateReturn()).thenReturn(devolutionDate);

        DetailBookResponse response = tested.rendBook(bookId);

        verify(findBookService).byId(bookId);
        verify(userAuthenticationService).get();
        verify(bookReturnCalculationService).calculateReturn();
        verify(bookRepository).save(book);

        assertEquals(userId, response.getResponsibleId());
        assertEquals(devolutionDate, response.getDevolutionDate());
    }

    @Test
    @DisplayName("Must not rent book when it is unavailable")
    void mustNotRentBookWhenItIsUnavailable(){

        User user = UserFactory.getUser();
        LocalDate inclusionDate = SimpleFactory.getNowDate();

        Book book = BookFactory.getRentedBook(user, inclusionDate);
        Long bookId = book.getId();

        when(findBookService.byId(bookId)).thenReturn(book);

        ResponseStatusException exception =
        assertThrows(ResponseStatusException.class, () -> {
            tested.rendBook(bookId);
        });

        verify(userAuthenticationService, never()).get();
        verify(bookReturnCalculationService, never()).calculateReturn();
        verify(bookRepository, never()).save(any());

        assertEquals("The book has already been rented!", exception.getReason());
    }

    @Test
    @DisplayName("Must not rent book when it does not exist")
    void mustNotRendBookWhenItDoesNotExist(){

        Long bookId = SimpleFactory.getRandomLong();

        doThrow(ResponseStatusException.class).when(findBookService).byId(bookId);

        assertThrows(ResponseStatusException.class, () -> {
            tested.rendBook(bookId);
        });

        verify(userAuthenticationService, never()).get();
        verify(bookReturnCalculationService, never()).calculateReturn();
        verify(bookRepository, never()).save(any());
    }
}
