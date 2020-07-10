package mk.ukim.finki.emt.onlineshop.customermanagement.domain.model;

import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.geo.Address;
import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.personalData.Name;
import org.springframework.lang.NonNull;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer extends AbstractEntity<CustomerId> {

    @Embedded
    private Name name;

    @Embedded
    private Address recipientAddress;

    private String email;

    public Customer(@NonNull Name name, @NonNull Address recipientAddress, @NonNull String email) {
        this.name = name;
        this.recipientAddress = recipientAddress;
        this.email = email;
    }

    protected Customer() {}

    public void setName(Name name) {
        this.name = name;
    }

    public void setBillingAddress(Address recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
