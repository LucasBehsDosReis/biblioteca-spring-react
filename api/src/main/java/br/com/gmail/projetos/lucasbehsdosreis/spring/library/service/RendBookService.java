package br.com.gmail.projetos.lucasbehsdosreis.spring.library.service;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.controller.response.DetailBookResponse;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Book;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.repository.BookRepository;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.domain.User;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.service.UserAuthenticationService;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.service.core.BookReturnCalculationService;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.service.core.FindBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static br.com.gmail.projetos.lucasbehsdosreis.spring.library.mapper.DetailBookMapper.toResponse;
import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class RendBookService {

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @Autowired
    private FindBookService findBookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookReturnCalculationService bookReturnCalculationService;

    @Transactional
    public DetailBookResponse rendBook(Long bookId) {

        Book book = findBookService.byId(bookId);

        if (nonNull(book.getResponsible())) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY, "The book has already been rented!");
        }

        User user = userAuthenticationService.get();

        book.setResponsible(user);
        book.setDevolutionDate(bookReturnCalculationService.calculateReturn());

        bookRepository.save(book);

        return toResponse(book);
    }
}
