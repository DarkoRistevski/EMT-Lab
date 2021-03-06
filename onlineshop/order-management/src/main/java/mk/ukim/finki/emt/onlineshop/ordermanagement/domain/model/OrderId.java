package mk.ukim.finki.emt.onlineshop.ordermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class OrderId extends DomainObjectId {

    protected OrderId() {
        super(DomainObjectId.randomId(OrderId.class).toString());
    }

    @JsonCreator
    public OrderId(@NonNull String id) {
        super(id);
    }
}
