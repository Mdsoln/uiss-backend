package com.uiss.home.models;

public record UpcomingEvent(
        String title,
        String description,
        String date,
        String time,
        String imageUrl
) {
}
