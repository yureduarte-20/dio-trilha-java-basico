package com.yure.complaints.application.request;

import jakarta.validation.constraints.NotBlank;

public record LoginResquest(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
