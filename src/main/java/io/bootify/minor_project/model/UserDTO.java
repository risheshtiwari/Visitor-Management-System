package io.bootify.minor_project.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 255)
    @UserEmailUnique
    private String email;

    @NotNull
    @Size(max = 255)
    @UserPhoneUnique
    private String phone;

    @NotNull
    @Size(max = 255)
    private String role;

    @NotNull
    private UserStatus status;

    private Long flat;

    private Long address;

}
