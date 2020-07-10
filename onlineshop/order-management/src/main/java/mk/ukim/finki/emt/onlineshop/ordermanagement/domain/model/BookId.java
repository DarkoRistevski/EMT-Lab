package mk.ukim.finki.emt.onlineshop.ordermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class BookId extends DomainObjectId {

    protected BookId() {
        super(DomainObjectId.randomId(BookId.class).toString());
    }

    @JsonCreator
    public BookId(String id) {
        super(id);
    }


}
