package mk.ukim.finki.emt.onlineshop.ordermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

import javax.persistence.Embeddable;

@Embeddable
public class OrderItemId extends DomainObjectId {

    protected OrderItemId() {
        super(DomainObjectId.randomId(OrderItemId.class).toString());
    }

    @JsonCreator
    public OrderItemId(@NonNull String id) {
        super(id);
    }


}
