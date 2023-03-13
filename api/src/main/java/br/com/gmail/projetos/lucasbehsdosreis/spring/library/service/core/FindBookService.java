package br.com.gmail.projetos.lucasbehsdosreis.spring.library.service.core;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.repository.BookRepository;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class FindBookService {

    @Autowired
    private BookRepository bookRepository;

    public Book byId(Long bookId) {
        return bookRepository
                .findById(bookId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Book not found!"));
    }
}
