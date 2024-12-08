package com.uiss.home.repository;

import com.uiss.home.entity.Testimonials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialsRepository extends JpaRepository<Testimonials, Integer> {
}
