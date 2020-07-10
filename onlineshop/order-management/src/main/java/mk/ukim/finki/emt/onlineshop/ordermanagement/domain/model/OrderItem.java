package mk.ukim.finki.emt.onlineshop.ordermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.financial.Price;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name="order_items")
@Getter
public class OrderItem extends AbstractEntity<OrderItemId> {

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="book_id",nullable = false))
    private BookId bookId;

    @Embedded
    private Price itemPrice;

    @Column(name = "qty", nullable = false)
    private int quantity;

    protected OrderItem() {
    }

    OrderItem(@NonNull BookId bookId, @NonNull Price itemPrice, @NonNull int quantity) {
        super(DomainObjectId.randomId(OrderItemId.class));
        setBookId(bookId);
        setItemPrice(itemPrice);
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    public void setBookId(BookId bookId) {
        this.bookId = bookId;
    }

    public void setItemPrice(Price itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    @NonNull
    @JsonProperty("qty")
    public int quantity() {
        return quantity;
    }

    public Price subtotal() {
        return itemPrice.multiply(quantity);
    }

}
