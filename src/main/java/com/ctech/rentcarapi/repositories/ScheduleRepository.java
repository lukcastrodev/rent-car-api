package com.ctech.rentcarapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ctech.rentcarapi.models.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
