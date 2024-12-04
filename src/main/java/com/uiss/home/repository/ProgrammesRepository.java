package com.uiss.home.repository;

import com.uiss.home.entity.Programmes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammesRepository extends JpaRepository<Programmes, Integer> {

}
