package br.com.gmail.projetos.lucasbehsdosreis.spring.library.service.core;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class ValidateBookService {

    @Autowired
    private BookRepository bookRepository;

    public void validateBookByTitle(String title) {

        boolean existsAnotherBookWithTheSameTitle = bookRepository.existsByTitle(title);

        if(existsAnotherBookWithTheSameTitle) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY, "The title must be unique!");
        }
    }
}
