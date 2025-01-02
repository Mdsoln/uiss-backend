package com.uiss.home.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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

    @Column(name = "home_id", nullable = false, length = 9)
    private String homeId;// automatically generated for querying and easily CRUD operations
    @Column(nullable = false)
    private String homeTitle;
    @Column(nullable = false)
    private String homeDescription;
    @Column(nullable = false)
    private String backgroundImageUrl;

}
