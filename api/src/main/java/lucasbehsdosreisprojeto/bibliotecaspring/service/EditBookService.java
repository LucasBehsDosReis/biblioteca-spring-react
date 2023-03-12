package lucasbehsdosreisprojeto.bibliotecaspring.service;

import lucasbehsdosreisprojeto.bibliotecaspring.controller.request.EditBookRequest;
import lucasbehsdosreisprojeto.bibliotecaspring.controller.response.DetailBookResponse;
import lucasbehsdosreisprojeto.bibliotecaspring.domain.Book;
import lucasbehsdosreisprojeto.bibliotecaspring.mapper.DetailBookMapper;
import lucasbehsdosreisprojeto.bibliotecaspring.repository.BookRepository;
import lucasbehsdosreisprojeto.bibliotecaspring.service.core.FindBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static lucasbehsdosreisprojeto.bibliotecaspring.domain.Status.ACTIVE;

@Service
public class EditBookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private FindBookService findBookService;

    public DetailBookResponse editBook(Long bookId, EditBookRequest request) {

        Book book = findBookService.byId(bookId);

        book.setTitle(request.getTitle());
        book.setStatus(ACTIVE);

        bookRepository.save(book);

        return DetailBookMapper.toResponse(book);
    }
}
