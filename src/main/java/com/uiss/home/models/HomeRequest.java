package com.uiss.home.models;

import jakarta.validation.constraints.NotNull;

public record HomeRequest(
        @NotNull(message = "Home title is required")
        String homeTitle,
        @NotNull(message = "Home description is required")
        String homeDescription,
        @NotNull(message = "Required background image for home page")
        String homeImage
) {
}
