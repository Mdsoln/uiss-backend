package com.uiss.home.entity;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "programmes")
@Builder
public class Programmes {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "program_generator")
    @SequenceGenerator(name = "program_generator", sequenceName = "program_seq", allocationSize = 1)
    private Integer id;

    private String about;
    private String mission;
    private String vision;
    private String youTubeUrl;
}
