package mk.ukim.finki.emt.onlineshop.sharedkernel.domain.personalData;

import lombok.Getter;
import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.base.ValueObject;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Embeddable
@MappedSuperclass
@Getter
public class Name implements ValueObject {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public Name(@NonNull String firstName, @NonNull String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @SuppressWarnings("unused")
    protected Name(){
        this.firstName = "";
        this.lastName = "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return firstName.equals(name.firstName) &&
                lastName.equals(name.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "Name: " +
                "firstName= " + firstName +
                ", lastName= " + lastName ;
    }

}
