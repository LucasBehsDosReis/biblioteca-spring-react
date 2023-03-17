package br.com.gmail.projetos.lucasbehsdosreis.spring.library.mapper;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.controller.response.DetailBookResponse;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Book;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.factories.BookFactory;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.factories.SimpleFactory;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.factories.UserFactory;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DetailBookMapperTest {

    @Test
    @DisplayName("Must return complete response when receiving complete book")
    void mustReturnCompleteResponseWhenReceivingCompleteBook() {

        User user = UserFactory.getUser();
        LocalDate today = SimpleFactory.getNowDate();
        LocalDate devolutionDate = SimpleFactory.getDevolutionDate(today);
        Book book = BookFactory.getRentedBook(user, devolutionDate);

        DetailBookResponse response = DetailBookMapper.toResponse(book);

        assertEquals(book.getId(), response.getId());
        assertEquals(book.getTitle(), response.getTitle());
        assertEquals(book.getInclusionDate(), response.getInclusionDate());
        assertEquals(book.getStatus(), response.getStatus());
        assertEquals(book.getDevolutionDate(), response.getDevolutionDate());
        assertEquals(book.getResponsible().getId(), response.getResponsibleId());
    }

    @Test
    @DisplayName("Must return response with responsible and devolution date null when book has not been rented")
    void mustReturnResponseWithResponsibleAndDevolutionDateNull() {

        Book book = BookFactory.getBook();

        DetailBookResponse response = DetailBookMapper.toResponse(book);

        assertNull(response.getDevolutionDate());
        assertNull(response.getResponsibleId());
    }

    @Test
    @DisplayName("Must return null response when book does not exist")
    void mustReturnNullResponseWhenBookDoesNotExist() {

        DetailBookResponse response = DetailBookMapper.toResponse(null);

        assertNotNull(response);
    }
}
