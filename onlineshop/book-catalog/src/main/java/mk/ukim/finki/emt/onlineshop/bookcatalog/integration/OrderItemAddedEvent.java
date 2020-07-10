package mk.ukim.finki.emt.onlineshop.bookcatalog.integration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.onlineshop.bookcatalog.domain.model.BookId;
import mk.ukim.finki.emt.onlineshop.bookcatalog.domain.model.OrderId;
import mk.ukim.finki.emt.onlineshop.bookcatalog.domain.model.OrderItemId;
import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;

@Getter
public class OrderItemAddedEvent implements DomainEvent {

    @JsonProperty("orderId")
    private final OrderId orderId;

    @JsonProperty("orderItemId")
    private final OrderItemId orderItemId;

    @JsonProperty("productId")
    private final BookId bookId;

    @JsonProperty("quantity")
    private final int quantity;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonCreator
    public OrderItemAddedEvent(OrderId orderId, OrderItemId orderItemId, BookId bookId, int quantity, Instant occurredOn) {
        this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.occurredOn = occurredOn;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

}
