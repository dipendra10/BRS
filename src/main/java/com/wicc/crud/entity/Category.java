package com.wicc.crud.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="brs_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    @Id
    @SequenceGenerator(name ="category_sequence",sequenceName = "category_sequence")
    @GeneratedValue(generator = "category_sequence",strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "name",length = 100)
    private String name;

    @Column(name = "description")
    @Size(max = 255)
    private String description;
}
