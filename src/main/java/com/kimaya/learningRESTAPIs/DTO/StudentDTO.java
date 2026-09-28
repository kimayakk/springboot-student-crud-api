package com.kimaya.learningRESTAPIs.DTO;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data annotation will automatically handle the commented code and will create equivalent byte code dyuring compilation
//based on annotation.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    private String name;
    @Email
    private String email;

}
