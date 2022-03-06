package com.wicc.crud.Dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
    private Integer id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String filePath;
    private MultipartFile multipartFile;
}
