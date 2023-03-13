package br.com.gmail.projetos.lucasbehsdosreis.spring.library.controller;

import br.com.gmail.projetos.lucasbehsdosreis.spring.library.controller.request.BookRequest;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.controller.request.EditBookRequest;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.controller.response.BookResponse;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.controller.response.DetailBookResponse;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.service.DeleteBookService;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.service.EditBookService;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.service.IncludeBookService;
import br.com.gmail.projetos.lucasbehsdosreis.spring.library.service.ListBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import static br.com.gmail.projetos.lucasbehsdosreis.spring.library.security.domain.Function.Names.ADMIN;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@CrossOrigin
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IncludeBookService includeBookService;

    @Autowired
    private EditBookService editBookService;

    @Autowired
    private DeleteBookService deleteBookService;

    @Autowired
    private ListBookService listBookService;

    @Secured(ADMIN)
    @PostMapping
    @ResponseStatus(CREATED)
    public BookResponse includeBook(@Valid @RequestBody BookRequest request) {
        return includeBookService.includeBook(request);
    }

    @Secured(ADMIN)
    @PutMapping("/{bookId}/edit")
    public DetailBookResponse editBook(@PathVariable Long bookId, @Valid @RequestBody EditBookRequest request) {
        return editBookService.editBook(bookId, request);
    }

    @Secured(ADMIN)
    @DeleteMapping("/{bookId}/delete")
    @ResponseStatus(NO_CONTENT)
    public void deleteBook(@PathVariable Long bookId) {
        deleteBookService.deleteBook(bookId);
    }

    @GetMapping
    public Page<BookResponse> listBookPageable(Pageable pageable) {
        return listBookService.listBookPageable(pageable);
    }
}
