package com.uiss.home.models;

public record QuoteRequest(
        String quote,
        String author,
        String position
) {
}
