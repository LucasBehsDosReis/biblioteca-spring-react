package br.com.gmail.projetos.lucasbehsdosreis.spring.library.service.core;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidateBookServiceTest {

    @InjectMocks
    private ValidateBookService tested;

    @Mock
    private BookRepository bookRepository;

    @Test
    @DisplayName("Must do nothing when title is unique")
    void mustDoNothingWhenTitleIsUnique() {
        String title = "Unique Title";

        when(bookRepository.existsByTitle(title)).thenReturn(false);

        tested.validateBookByTitle(title);

        verify(bookRepository).existsByTitle(title);
    }

    @Test
    @DisplayName("Must throw error when title is not unique")
    void mustThrowErrorWhenTitleIsNotUnique() {
        String title = "Non-Unique Title";

        when(bookRepository.existsByTitle(title)).thenReturn(true);

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class, () -> tested.validateBookByTitle(title));

        assertEquals("The title must be unique!", exception.getReason());
    }
}
