package com.uiss.home.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "testimonials")
@Builder
public class Testimonials {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "testimonials_generator")
    @SequenceGenerator(name = "testimonials_generator", sequenceName = "testimonials_seq", allocationSize = 1)
    private Integer id;

    private String description;
    private String imageUrl;
    private String position;
    private String fullname;
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate(){
        this.createdAt = LocalDateTime.now().withNano(0);
    }
}
