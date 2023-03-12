package lucasbehsdosreisprojeto.bibliotecaspring.controller;

import jakarta.validation.Valid;
import lucasbehsdosreisprojeto.bibliotecaspring.controller.request.BookRequest;
import lucasbehsdosreisprojeto.bibliotecaspring.controller.request.EditBookRequest;
import lucasbehsdosreisprojeto.bibliotecaspring.controller.response.BookResponse;
import lucasbehsdosreisprojeto.bibliotecaspring.controller.response.DetailBookResponse;
import lucasbehsdosreisprojeto.bibliotecaspring.service.DeleteBookService;
import lucasbehsdosreisprojeto.bibliotecaspring.service.EditBookService;
import lucasbehsdosreisprojeto.bibliotecaspring.service.IncludeBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IncludeBookService includeBookService;

    @Autowired
    private EditBookService editBookService;
    private DeleteBookService deleteBookService;

    @PostMapping
    @ResponseStatus(CREATED)
    public BookResponse includeBook(@Valid @RequestBody BookRequest request) {
        return includeBookService.includeBook(request);
    }

    @PutMapping("/{bookId}/edit")
    public DetailBookResponse editBook(@PathVariable Long bookId, @Valid @RequestBody EditBookRequest request) {
        return editBookService.editBook(bookId, request);
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteBook(@PathVariable Long bookId) {
        deleteBookService.deleteBook(bookId);
    }
}
