package com.uiss.home.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProgramRequest(
        @NotNull(message = "Program description is required")
        @NotEmpty(message = "Program description can not be blank")
        String about,
        @NotNull(message = "Program mission is required")
        @NotEmpty(message = "Program mission can not be blank")
        String mission,
        @NotNull(message = "Program vision is required")
        @NotEmpty(message = "Program vision can not be blank")
        String vision,
        @NotNull(message = "YouTube link is mandatory")
        @NotEmpty(message = "YouTube link can not be blank")
        String youTubeUrl
) {
}
