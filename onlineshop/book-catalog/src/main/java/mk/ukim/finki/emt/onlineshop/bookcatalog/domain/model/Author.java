package mk.ukim.finki.emt.onlineshop.bookcatalog.domain.model;

import lombok.Data;
import mk.ukim.finki.emt.onlineshop.sharedkernel.domain.personalData.Name;

import javax.persistence.*;

@Entity
@Data
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Embedded
    private Name name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

}
