package com.wicc.crud.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="brs_book")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @Id
    @SequenceGenerator(name = "book_id_sequence", sequenceName = "book_id_sequence")
    @GeneratedValue(generator = "book_id_sequence",strategy = GenerationType.SEQUENCE )
    private Integer id;

    @Column(name = "name",length = 200)
    private String name;

    private Integer numberOfPages;

    @Column(name = "isbn_number",length = 30)
    private String isbnNumber;

    private Double rating;

    private Integer stockCount;

    private Date publishedDate;

    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",referencedColumnName ="id",
            foreignKey = @ForeignKey(name = "FK_Book_Category"))
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "book_author")
    private List<Author> authorList;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<BookTransaction> bookTransactions = new HashSet<>();

}
