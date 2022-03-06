package com.wicc.crud.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private Integer id;

    @NotBlank
    @Size(min=3,max = 20,message = "length must be in between 3 to 20")
    private String name;
    private String description;
}
