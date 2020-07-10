package mk.ukim.finki.emt.onlineshop.ordermanagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.financial.Price;

import java.util.Set;

@Getter
public class Book {

    private BookId id;

    private String title;

    private int releaseYear;

    private Price price;

    private int quantity;

    private Set<String> authors;
}
