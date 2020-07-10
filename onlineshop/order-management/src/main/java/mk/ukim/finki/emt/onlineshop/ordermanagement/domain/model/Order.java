package mk.ukim.finki.emt.onlineshop.ordermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.financial.Price;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

@Entity
@Getter
@Table(name = "orders")
public class Order extends AbstractEntity<OrderId> {

    @Version
    private Long version;

    @Column(name = "ordered_on", nullable = false)
    private Instant orderedOn;

    @Column(name = "order_currency", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "order_state", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderState state;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<OrderItem> items;

    @SuppressWarnings("unused")
    protected Order() {

    }

    public Order(@NonNull Instant orderedOn, @NonNull Currency currency) {
        super(DomainObjectId.randomId(OrderId.class));
        this.items = new HashSet<>();
        setCurrency(currency);
        setOrderedOn(orderedOn);
        setState(OrderState.RECEIVED);
    }

    @NonNull
    @JsonProperty("state")
    public OrderState state() {
        return state;
    }

    private void setState(@NonNull OrderState state) {
        this.state = state;
    }

    public void setOrderedOn(Instant orderedOn) {
        this.orderedOn = orderedOn;
    }

    public Stream<OrderItem> getItems() {
        return items.stream();
    }

    public OrderItem addItem(@NonNull Book book, int qty) {
        Objects.requireNonNull(book, "product must not be null");
        var item = new OrderItem(book.getId(), book.getPrice(), qty);
        item.setQuantity(qty);
        items.add(item);
        return item;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }


    public Price total() {
        return items.stream().map(OrderItem::subtotal).reduce(new Price(currency, 0), Price::add);
    }

    public Instant getOrderedOn() {
        return orderedOn;
    }


}
