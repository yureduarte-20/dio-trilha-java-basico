package com.yure.complaints.application.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record CategoryCreateRequest(
        @NotBlank
        String name,
        @Nullable
        String description
) {
}
