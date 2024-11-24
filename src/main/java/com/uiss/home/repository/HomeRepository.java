package com.uiss.home.repository;

import com.uiss.home.entity.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRepository extends JpaRepository<Home, Integer> {

    Home findByHomeId(String homeId);
}
