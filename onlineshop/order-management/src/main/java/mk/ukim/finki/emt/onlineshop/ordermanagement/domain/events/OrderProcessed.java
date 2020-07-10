package mk.ukim.finki.emt.onlineshop.ordermanagement.domain.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import mk.ukim.finki.emt.onlineshop.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;

public class OrderProcessed implements DomainEvent {

    @JsonProperty("orderId")
    private final OrderId orderId;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    public OrderProcessed(OrderId orderId, Instant occurredOn){
        this.orderId = orderId;
        this.occurredOn = occurredOn;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

}
