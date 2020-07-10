package mk.ukim.finki.emt.onlineshop.bookcatalog.domain.repository;

import mk.ukim.finki.emt.onlineshop.bookcatalog.domain.model.Book;
import mk.ukim.finki.emt.onlineshop.bookcatalog.domain.model.BookId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, BookId> {
    Optional<Object> findById(mk.ukim.finki.emt.onlineshop.ordermanagement.domain.model.BookId bookId);
}

