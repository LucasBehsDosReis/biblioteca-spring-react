package br.com.gmail.projetos.lucasbehsdosreis.spring.library.service;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.controller.request.BookRequest;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Book;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.mapper.BookMapper;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.repository.BookRepository;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.service.core.NowService;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.service.core.ValidateBookService;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.controller.response.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Status.ACTIVE;

@Service
public class IncludeBookService {

    @Autowired
    private ValidateBookService validateBookService;

    @Autowired
    private NowService nowService;

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public BookResponse includeBook(BookRequest request) {

        Book book = BookMapper.toEntity(request);

        validateBookService.validateBookByTitle(request.getTitle());

        book.setStatus(ACTIVE);
        book.setInclusionDate(nowService.getDate());

        bookRepository.save(book);

        return BookMapper.toResponse(book);
    }
}
