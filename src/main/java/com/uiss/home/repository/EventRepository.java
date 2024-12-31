package com.uiss.home.repository;

import com.uiss.home.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query("SELECT e.title, e.description, e.date, e.time, e.imageUrl, e.dayOfWeek FROM Event e")
    Page<Object[]> findAllUpcomingEvents(Pageable pageable);
}
