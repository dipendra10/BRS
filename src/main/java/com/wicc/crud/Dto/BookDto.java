package com.wicc.crud.Dto;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private Integer id;

    private String name;

    private Integer numberOfPages;

    private String isbnNumber;

    private Double rating;

    private Integer stockCount;

    private Date publishedDate;

    private String filePath;

    private MultipartFile multipartFile;

    private List<Integer> authorDtoList;

    private Integer categoryDto;

}
