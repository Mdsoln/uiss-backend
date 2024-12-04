package com.uiss.home.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "start_with_you")
@Builder
public class StartWith {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "start_with_generator")
    @SequenceGenerator(name = "start_with_generator", sequenceName = "start_with_seq", allocationSize = 1)
    private Integer id;

    private String title;
    private String description;
    private String imagePath;
}
