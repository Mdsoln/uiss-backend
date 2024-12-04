package com.uiss.home.repository;

import com.uiss.home.entity.StartWith;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StartWithYouRepository extends JpaRepository<StartWith, Integer> {

}
