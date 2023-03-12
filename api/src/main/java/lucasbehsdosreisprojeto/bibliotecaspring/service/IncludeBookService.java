package lucasbehsdosreisprojeto.bibliotecaspring.service;

import lucasbehsdosreisprojeto.bibliotecaspring.controller.request.BookRequest;
import lucasbehsdosreisprojeto.bibliotecaspring.controller.response.BookResponse;
import lucasbehsdosreisprojeto.bibliotecaspring.domain.Book;
import lucasbehsdosreisprojeto.bibliotecaspring.mapper.BookMapper;
import lucasbehsdosreisprojeto.bibliotecaspring.repository.BookRepository;
import lucasbehsdosreisprojeto.bibliotecaspring.service.core.NowService;
import lucasbehsdosreisprojeto.bibliotecaspring.service.core.ValidateBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static lucasbehsdosreisprojeto.bibliotecaspring.domain.Status.ACTIVE;

@Service
public class IncludeBookService {

    @Autowired
    private ValidateBookService validateBookService;

    @Autowired
    private NowService nowService;

    @Autowired
    private BookRepository bookRepository;

    public BookResponse includeBook(BookRequest request) {

        Book book = BookMapper.toEntity(request);

        validateBookService.validateBookByTitle(request.getTitle());

        book.setStatus(ACTIVE);
        book.setInclusionDate(nowService.getDate());

        bookRepository.save(book);

        return BookMapper.toResponse(book);
    }
}
