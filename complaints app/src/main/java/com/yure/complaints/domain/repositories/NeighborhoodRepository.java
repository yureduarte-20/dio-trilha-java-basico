package com.yure.complaints.domain.repositories;

import com.yure.complaints.domain.models.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long> {

}
