package pet_studio.pet_studio_spring.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public record SignInDto(
        @NotBlank String email,
        @NotBlank String password
) {
}
