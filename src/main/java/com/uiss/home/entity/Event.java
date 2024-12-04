package com.uiss.home.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "events")
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private String date; // Format: YYYY-MM-DD
    private String time; // Format: HH:mm - HH:mm
    private String imageUrl;
    private String dayOfWeek;
}
