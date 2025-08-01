package io.devotel.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserDTO {

    @NotBlank(message = "Must not be blank")
    private String name;

    @NotBlank(message = "Must not be blank")
    @Email(message = "Must be in valid format")
    private String email;


}
