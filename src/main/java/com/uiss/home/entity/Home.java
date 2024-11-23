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

    @Column(nullable = false, length = 9)
    private String homeId;// automatically generated for querying and easily CRUD operations
    @Size(max = 160)
    @Column(nullable = false, length = 160)
    private String homeTitle;
    @Column(nullable = false)
    private String homeDescription;
    @Size(max = 500)
    @Column(nullable = false, length = 500)
    private String backgroundImageUrl;

}
