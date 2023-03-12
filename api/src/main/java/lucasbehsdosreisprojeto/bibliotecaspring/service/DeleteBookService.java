package lucasbehsdosreisprojeto.bibliotecaspring.service;

import lucasbehsdosreisprojeto.bibliotecaspring.domain.Book;
import lucasbehsdosreisprojeto.bibliotecaspring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static lucasbehsdosreisprojeto.bibliotecaspring.domain.Status.INACTIVE;

@Service
public class DeleteBookService {

    @Autowired
    private BookRepository bookRepository;

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
