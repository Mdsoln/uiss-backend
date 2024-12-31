package com.uiss.home.repository;

import com.uiss.home.entity.Testimonials;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialsRepository extends JpaRepository<Testimonials, Integer> {
    @Query("SELECT t.description, t.imageUrl, t.position, t.fullname FROM Testimonials t")
    Page<Object[]> findAllTestimonials(Pageable pageable);
}
