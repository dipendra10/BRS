package com.wicc.crud.Dto;

import lombok.*;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AuthorDto {
    private Integer id;

    @NotBlank
    @Size(min=3,max = 20,message = "length must be in between 3 to 20")
    private String name;

    @Email(regexp = "^(.+)@(.+)$",message = "Invalid Email!!")
    @NotEmpty(message = "Email should not be empty")
    private String email;

    @NotNull
    @Size(max = 10, min = 10, message = "Mobile number should be of 10 digits")
    @Pattern(regexp = "[7-9][0-9]{9}", message = "Mobile number is invalid!!")
    private String mobileNumber;
}
