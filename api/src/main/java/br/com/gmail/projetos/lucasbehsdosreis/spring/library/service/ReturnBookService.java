package br.com.gmail.projetos.lucasbehsdosreis.spring.library.service;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Book;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.repository.BookRepository;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.service.core.FindBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class ReturnBookService {

    @Autowired
    private FindBookService findBookService;

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public void returnBook(Long bookId) {

        Book book = findBookService.byId(bookId);

        if (isNull(book.getResponsible())) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY, "The book has not been rented!");
        }

        book.setResponsible(null);
        book.setDevolutionDate(null);

        bookRepository.save(book);
    }
}
