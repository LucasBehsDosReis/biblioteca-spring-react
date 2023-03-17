package br.com.gmail.projetos.lucasbehsdosreis.spring.library.service;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Book;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.factories.BookFactory;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Status.INACTIVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteBookServiceTest {

    @InjectMocks
    private DeleteBookService tested;

    @Mock
    private BookRepository bookRepository;

    @Test
    @DisplayName("Must successfully delete book")
    void mustSuccessfullyDeleteBook() {

        Book book = BookFactory.getBook();
        Long bookId = book.getId();

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        tested.deleteBook(bookId);

        verify(bookRepository).findById(bookId);
        verify(bookRepository).save(book);

        assertEquals(INACTIVE, book.getStatus());
    }
}
