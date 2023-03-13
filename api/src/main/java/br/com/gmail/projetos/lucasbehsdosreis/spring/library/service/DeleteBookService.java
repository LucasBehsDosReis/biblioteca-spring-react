package br.com.gmail.projetos.lucasbehsdosreis.spring.library.service;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Book;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Status.INACTIVE;

@Service
public class DeleteBookService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public void deleteBook(Long bookId) {

        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (bookOptional.isEmpty()) {
            return;
        }

        Book book = bookOptional.get();
        book.setStatus(INACTIVE);

        bookRepository.save(book);
    }
}
