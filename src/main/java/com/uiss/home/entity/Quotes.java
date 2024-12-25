package com.uiss.home.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "quotes")
@Builder
public class Quotes {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quote_generator")
    @SequenceGenerator(name = "quote_generator", sequenceName = "quote_seq", allocationSize = 1)
    private Integer id;

    private String quote;
    private String author;
    private String position;
}
