package br.com.gmail.projetos.lucasbehsdosreis.spring.library.service;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.controller.request.EditBookRequest;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.controller.response.DetailBookResponse;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Book;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.mapper.DetailBookMapper;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.repository.BookRepository;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.service.core.FindBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.gmail.projetos.lucasbehsdosreis.spring.library.domain.Status.ACTIVE;

@Service
public class EditBookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private FindBookService findBookService;

    @Transactional
    public DetailBookResponse editBook(Long bookId, EditBookRequest request) {

        Book book = findBookService.byId(bookId);

        book.setTitle(request.getTitle());
        book.setStatus(ACTIVE);

        bookRepository.save(book);

        return DetailBookMapper.toResponse(book);
    }
}
