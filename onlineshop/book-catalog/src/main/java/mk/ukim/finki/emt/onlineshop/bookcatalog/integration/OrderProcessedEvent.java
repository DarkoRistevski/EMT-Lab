package mk.ukim.finki.emt.onlineshop.bookcatalog.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.onlineshop.bookcatalog.domain.model.OrderId;
import mk.ukim.finki.emt.onlineshop.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;

@Getter
public class OrderProcessedEvent implements DomainEvent {

    @JsonProperty("orderId")
    private final Order order;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    public OrderProcessedEvent(Order order, Instant occurredOn){
        this.order = order;
        this.occurredOn = occurredOn;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

}
