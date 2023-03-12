package lucasbehsdosreisprojeto.bibliotecaspring.repository;

import lucasbehsdosreisprojeto.bibliotecaspring.domain.Book;
import lucasbehsdosreisprojeto.bibliotecaspring.domain.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findAllByStatus(Status status, Pageable pageable);

    boolean existsByTitle(String title);
}
