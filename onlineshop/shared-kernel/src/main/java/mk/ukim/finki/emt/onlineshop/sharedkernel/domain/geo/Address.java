package mk.ukim.finki.emt.onlineshop.sharedkernel.domain.geo;

import lombok.Getter;
import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.base.ValueObject;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
@MappedSuperclass
@Getter
public class Address implements ValueObject {

    @Column(name = "street")
    private final String street;

    @Column(name = "number")
    private final String number;

    @Column(name = "zip")
    private final int zip;

    @Column(name = "city")
    private final String city;

    @Column(name = "country")
    @Enumerated(EnumType.STRING)
    private final Country country;


    public Address(String street, String number, int zip, String city, Country country) {
        this.street = street;
        this.number = number;
        this.zip = zip;
        this.city = city;
        this.country = country;
    }

    @SuppressWarnings("unused")
    protected Address() {
        this.street = "";
        this.number = "";
        this.zip = 0;
        this.city = "";
        this.country = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return zip == address.zip &&
                street.equals(address.street) &&
                number.equals(address.number) &&
                city.equals(address.city) &&
                country == address.country;
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, number, zip, city, country);
    }

    @Override
    public String toString() {
        return "Address: " +
                "street='" + street +
                ", number='" + number +
                ", zip=" + zip +
                ", city='" + city +
                ", country=" + country;
    }
}
