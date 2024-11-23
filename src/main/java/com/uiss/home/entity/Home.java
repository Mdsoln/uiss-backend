package com.uiss.home.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "home")
@Builder
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "home_generator")
    @SequenceGenerator(name = "home_generator", sequenceName = "home_seq", allocationSize = 1)
    private Integer id;

    private String homeTitle;
    private String homeDescription;
    private String backgroundImage;

}
