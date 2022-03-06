package com.wicc.crud.Dto;


import com.wicc.crud.enums.RentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookTransactionDto {
    private Integer id;

    private Integer code;

    private Date fromDate;

    private Date toDate;

    private String rentTypes;

    private String activeClosed;

    private Integer bookDto;

    private Integer memberDto;
}
