package com.wicc.crud.entity;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;


@Entity
@Table(name = "tbl_author")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {
    @Id
    @SequenceGenerator(name ="author_sequence",sequenceName = "author_sequence")
    @GeneratedValue(generator = "author_sequence",strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "name",length = 100)
    private String name;

    @Column(name="email" ,length=50)
    private String email;

    @Column(name = "mobile_number",length = 10)
    private String mobileNumber;
}
