package mk.ukim.finki.emt.onlineshop.bookcatalog.application;

import mk.ukim.finki.emt.onlineshop.bookcatalog.domain.model.Book;
import mk.ukim.finki.emt.onlineshop.bookcatalog.domain.model.BookId;
import mk.ukim.finki.emt.onlineshop.bookcatalog.domain.repository.BookRepository;
import mk.ukim.finki.emt.onlineshop.bookcatalog.integration.OrderItemAddedEvent;
import mk.ukim.finki.emt.onlineshop.bookcatalog.integration.OrderProcessedEvent;
import mk.ukim.finki.emt.onlineshop.ordermanagement.domain.repository.OrderRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class BookCatalog {

    private final BookRepository bookRepository;

    public BookCatalog(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @NonNull
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @NonNull
    public Optional<Book> findById(@NonNull BookId bookId) {
        Objects.requireNonNull(bookId, "productId must not be null");
        return bookRepository.findById(bookId);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onOrderCreatedEvent(    OrderItemAddedEvent event) {
        Book b = bookRepository.findById(event.getBookId()).orElseThrow(RuntimeException::new);
        b.subtractQuantity(event.getQuantity());
        bookRepository.save(b);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onOrderCreatedEvent(    OrderProcessedEvent event) {
        event.getOrder().getItems().forEach(orderItem -> {
            Book b = (Book) bookRepository.findById(orderItem.getBookId()).orElseThrow(RuntimeException::new);
            b.subtractQuantity(orderItem.getQuantity());
            bookRepository.save(b);
        });
    }
}

