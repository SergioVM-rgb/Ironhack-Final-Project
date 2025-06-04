package com.ironhack.project.repositories;

import com.ironhack.project.models.Campaing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaingRepository extends JpaRepository<Campaing, Integer> {
}
