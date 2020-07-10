package mk.ukim.finki.emt.onlineshop.sharedkernel.domain.financial;

import lombok.Getter;
import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.base.ValueObject;
import org.springframework.lang.NonNull;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
@Getter
public class Price implements ValueObject {

    @Enumerated(value = EnumType.STRING)
    private final Currency currency;

    private final int amount;

    public Price(@NonNull Currency currency, @NonNull int amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public static Price valueOf(Currency currency, int amount) {
        return new Price(currency, amount);
    }

    @SuppressWarnings("unused")
    protected Price() {
        this.currency = Currency.MKD;
        this.amount = 0;
    }

    public Price add(Price price) {
        if (!currency.equals(price.currency)) {
            throw new IllegalArgumentException("Cannot add two Price objects with different currencies");
        }
        return new Price(currency, amount + price.amount);
    }

    public Price subtract(Price price) {
        if (!currency.equals(price.currency)) {
            throw new IllegalArgumentException("Cannot add two Price objects with different currencies");
        }
        return new Price(currency, amount - price.amount);
    }

    public Price multiply(int m) {
        return new Price(currency, amount * m);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return amount == price.amount &&
                currency == price.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }

    @Override
    public String toString() {
        return "Price: " +
                "currency=" + currency +
                ", amount=" + amount;
    }


}
