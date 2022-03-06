package com.wicc.crud.entity;


import com.wicc.crud.enums.RentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookTransaction {
    @Id
    @SequenceGenerator(name = "bookTransaction_id_sequence", sequenceName = "bookTransaction_id_sequence")
    @GeneratedValue(generator = "bookTransaction_id_sequence",strategy = GenerationType.SEQUENCE )
    private Integer id;

    private Integer code;

    private Date fromDate;

    private Date toDate;

    @Column(name = "rent_types")
    @Enumerated(EnumType.STRING)
    private RentStatus rentTypes;

    @Column(name = "active_closed",nullable = false)
    private String activeClosed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Book_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_BookTransaction_Book"))
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Member_id",referencedColumnName = "id",foreignKey = @ForeignKey(name="fk_BookTransaction_Member"))
    private Member member;

}
