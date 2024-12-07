package com.uiss.home.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpcomingEvent(
        @NotNull(message = "Event title is mandatory")
        @NotBlank(message = "Event title can not be blank")
        @NotEmpty(message = "Event title is mandatory")
        String title,
        @NotNull(message = "Event description is mandatory")
        String description,
        @NotNull(message = "Event date is mandatory")
        String date,
        @NotNull(message = "Event time is mandatory")
        String time,
        @NotNull(message = "Image url is mandatory")
        String imageUrl
) {
}
