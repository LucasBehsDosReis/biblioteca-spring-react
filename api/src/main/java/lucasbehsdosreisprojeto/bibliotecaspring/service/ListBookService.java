package lucasbehsdosreisprojeto.bibliotecaspring.service;

import lucasbehsdosreisprojeto.bibliotecaspring.controller.response.BookResponse;
import lucasbehsdosreisprojeto.bibliotecaspring.domain.Book;
import lucasbehsdosreisprojeto.bibliotecaspring.mapper.BookMapper;
import lucasbehsdosreisprojeto.bibliotecaspring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static lucasbehsdosreisprojeto.bibliotecaspring.domain.Status.ACTIVE;

@Service
public class ListBookService {

    @Autowired
    private BookRepository bookRepository;

    public Page<BookResponse> listBookPageable(Pageable pageable) {

        Page<Book> books = bookRepository.findAllByStatus(ACTIVE, pageable);

        return books.map(BookMapper::toResponse);
    }
}
