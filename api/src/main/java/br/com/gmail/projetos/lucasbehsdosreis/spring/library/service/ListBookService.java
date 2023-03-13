package br.com.gmail.projetos.lucasbehsdosreis.spring.library.service;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Book;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.mapper.BookMapper;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.repository.BookRepository;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.controller.response.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Status.ACTIVE;

@Service
public class ListBookService {

    @Autowired
    private BookRepository bookRepository;

    public Page<BookResponse> listBookPageable(Pageable pageable) {

        Page<Book> books = bookRepository.findAllByStatus(ACTIVE, pageable);

        return books.map(BookMapper::toResponse);
    }
}