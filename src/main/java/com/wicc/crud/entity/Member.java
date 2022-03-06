package com.wicc.crud.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "brs_member",uniqueConstraints = {
        @UniqueConstraint(name ="unique_member_email",columnNames = "email"),
        @UniqueConstraint(name ="unique_member_mobile number",columnNames = "mobile_number")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member implements Serializable {
    @Id
    @SequenceGenerator(name ="member_sequence",sequenceName = "member_sequence")
    @GeneratedValue(generator = "member_sequence",strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "name",length = 100)
    private String name;

    @Column(name="email" ,length=50)
    private String email;

    @Column(name = "mobile_number",length = 10)
    private String mobileNumber;

    private String address;

    private String filePath;
}
