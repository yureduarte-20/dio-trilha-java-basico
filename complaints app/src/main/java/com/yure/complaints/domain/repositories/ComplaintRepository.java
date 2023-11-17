package com.yure.complaints.domain.repositories;

import com.yure.complaints.domain.models.Category;
import com.yure.complaints.domain.models.Complaint;
import com.yure.complaints.domain.models.Neighborhood;
import com.yure.complaints.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findAllByCategory(Category category);
    List<Complaint> findAllByNeighborhood(Neighborhood neighborhood);
    List<Complaint> findAllByUser(User user);
}
