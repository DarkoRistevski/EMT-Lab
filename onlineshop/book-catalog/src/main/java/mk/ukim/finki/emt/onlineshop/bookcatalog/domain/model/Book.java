package mk.ukim.finki.emt.onlineshop.bookcatalog.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.financial.Price;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name = "book")
public class Book extends AbstractEntity<BookId> {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "release_year", nullable = false)
    private int releaseYear;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name = "amount")),
            @AttributeOverride(name="currency", column = @Column(name = "currency"))
    })
    private Price price;

    @Column(name = "available_quantity", nullable = false)
    private int quantity;

    @OneToMany(mappedBy = "book")
    private Set<Author> authors;

    public Book(@NonNull String title, @NonNull int releaseYear, @NonNull Price price, @NonNull int availableQuantity) {
        this.authors = new HashSet<>();
        this.title = title;
        this.releaseYear = releaseYear;
        this.price = price;
        this.quantity = availableQuantity;
    }

    protected Book() {}

    @JsonProperty("title")
    public String getName() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public void setAvailableQuantity(int availableQuantity) {
        if (availableQuantity < 0) {
            throw new IllegalArgumentException("Available quantity cannot be negative");
        }
        this.quantity = availableQuantity;
    }

    public void subtractQuantity(int qnt) {
        if (qnt>this.quantity) {
            throw new RuntimeException("unsupported quantity");
        }
        this.quantity -= qnt;
    }

    public void addQuantity(int qnt) {
        this.quantity +=qnt;
    }

}
