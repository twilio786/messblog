package com.mess.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessDto {
    private long id;
    @NotEmpty
    @Size(min = 2,message = "name should be minimum 2 character")
    private String name;
    @NotEmpty
    @Email
    private String email;
}

