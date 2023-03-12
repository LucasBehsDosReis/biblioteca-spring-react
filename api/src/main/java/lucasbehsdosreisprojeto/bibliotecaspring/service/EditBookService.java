package lucasbehsdosreisprojeto.bibliotecaspring.service;

import lucasbehsdosreisprojeto.bibliotecaspring.controller.request.EditBookRequest;
import lucasbehsdosreisprojeto.bibliotecaspring.controller.response.DetailBookResponse;
import lucasbehsdosreisprojeto.bibliotecaspring.domain.Book;
import lucasbehsdosreisprojeto.bibliotecaspring.mapper.DetailBookMapper;
import lucasbehsdosreisprojeto.bibliotecaspring.mapper.EditBookMapper;
import lucasbehsdosreisprojeto.bibliotecaspring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditBookService {

    @Autowired
    private BookRepository bookRepository;

    public DetailBookResponse editBook(Long bookId, EditBookRequest request) {

        Book book = EditBookMapper.toEntity(request);

        bookRepository.save(book);

        return DetailBookMapper.toResponse(book);
    }
}
