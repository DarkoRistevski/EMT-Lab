package mk.ukim.finki.emt.onlineshop.customermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.base.DomainObjectId;

public class CustomerId extends DomainObjectId {

    protected CustomerId() {
        super(DomainObjectId.randomId(CustomerId.class).toString());
    }

    @JsonCreator
    public CustomerId(String id) {
        super(id);
    }

}
