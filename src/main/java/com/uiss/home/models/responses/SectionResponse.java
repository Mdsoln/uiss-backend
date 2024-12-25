package com.uiss.home.models.responses;

import lombok.Builder;

@Builder
public record SectionResponse(
        String title,
        String description,
        String imageUrl
) {
}
