package com.uiss.home.repository;

import com.uiss.home.entity.Quotes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotesRepository extends JpaRepository<Quotes, Integer> {

    @Query("SELECT q.id, q.quote, q.author, q.position FROM Quotes q")
    Page<Object[]> findAllQuotes(Pageable pageable);
}
