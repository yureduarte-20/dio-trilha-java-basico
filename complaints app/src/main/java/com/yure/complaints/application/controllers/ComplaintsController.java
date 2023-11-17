package com.yure.complaints.application.controllers;

import com.yure.complaints.application.exceptions.EntityNotFoundException;
import com.yure.complaints.application.request.complaints.ComplaintCreateRequest;
import com.yure.complaints.domain.dtos.ComplaintDTO;
import com.yure.complaints.domain.dtos.UserDTO;
import com.yure.complaints.domain.models.Category;
import com.yure.complaints.domain.models.Complaint;
import com.yure.complaints.domain.models.Neighborhood;
import com.yure.complaints.domain.models.User;
import com.yure.complaints.domain.repositories.CategoryRepository;
import com.yure.complaints.domain.repositories.ComplaintRepository;
import com.yure.complaints.domain.repositories.NeighborhoodRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.Valid;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/complaint")
public class ComplaintsController {
    public static int MAX_SIZE_PER_PAGE = 20;
    private final ComplaintRepository complaintRepository;
    private final CategoryRepository categoryRepository;
    private final NeighborhoodRepository neighborhoodRepository;
    private final EntityManager entityManager;
    public ComplaintsController(ComplaintRepository complaintRepository, CategoryRepository categoryRepository, NeighborhoodRepository neighborhoodRepository, EntityManager entityManager) {

        this.complaintRepository = complaintRepository;
        this.categoryRepository = categoryRepository;
        this.neighborhoodRepository = neighborhoodRepository;
        this.entityManager = entityManager;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Complaint> create(@RequestBody @Valid ComplaintCreateRequest complaintCreateRequest) throws EntityNotFoundException {
        var user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var category = this.categoryRepository.findById(complaintCreateRequest.categoryId()).orElseThrow(() -> new EntityNotFoundException(complaintCreateRequest.categoryId(), Category.class));
        var neig = neighborhoodRepository.findById(complaintCreateRequest.categoryId()).orElseThrow(() -> new EntityNotFoundException(complaintCreateRequest.neighborhoodId(), Neighborhood.class));
        var complaint = ComplaintCreateRequest.toComplaint(complaintCreateRequest, user, category, neig);
        complaint = complaintRepository.save(complaint);
        var uri = java.net.URI.create("/complaint/" + complaint.getId());
        return  ResponseEntity.created(uri).body(complaint);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<List<?> > getComplaintsByParameter(@RequestParam("category")Optional<Long> id,
                                                                      @RequestParam("user")Optional<Long> userId,
                                                                      @RequestParam("neighborhood")Optional<Long> neighborhoodId){
        CriteriaQuery<Complaint> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Complaint.class);
        Root<Complaint> root = criteriaQuery.from(Complaint.class);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        List<Predicate> predicates = new ArrayList<>();
        if (id.isPresent()) {
            Predicate categoryIdPredicate = criteriaBuilder.equal(root.get("category").get("id"), id.get());
            predicates.add(categoryIdPredicate);
        }

        if (userId.isPresent()) {
            Predicate userIdPredicate = criteriaBuilder.equal(root.get("user").get("id"), userId.get());
            predicates.add(userIdPredicate);
        }

        if (neighborhoodId.isPresent()) {
            Predicate neighborhoodIdPredicate = criteriaBuilder.equal(root.get("neighborhood").get("id"), neighborhoodId.get());
            predicates.add(neighborhoodIdPredicate);
        }
        if (!predicates.isEmpty()) {
            Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            criteriaQuery.where(finalPredicate);
        }

        TypedQuery<Complaint> typedQuery = entityManager.createQuery(criteriaQuery);

        List<ComplaintDTO> complaints = typedQuery.getResultList().stream().map(ComplaintDTO::fromComplaint).toList();
        return ResponseEntity.ok(complaints);
    }
    @GetMapping("/my-complaints")
    ResponseEntity<List<ComplaintDTO>> getMyComplaints(){
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return  ResponseEntity.ok(complaintRepository.findAllByUser(user).stream().map(ComplaintDTO::fromComplaint).toList());
    }
}
