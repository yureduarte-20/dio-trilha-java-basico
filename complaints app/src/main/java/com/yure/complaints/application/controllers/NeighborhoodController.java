package com.yure.complaints.application.controllers;

import com.yure.complaints.application.exceptions.EntityNotFoundException;
import com.yure.complaints.application.request.neighborhood.NeighborhoodUpdateRequest;
import com.yure.complaints.application.request.neighborhood.NeighborhoodCreateRequest;
import com.yure.complaints.domain.models.Neighborhood;
import com.yure.complaints.domain.repositories.NeighborhoodRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/neighborhood")
@SecurityRequirement(name = "Bearer Authentication")
public class NeighborhoodController {
    private final NeighborhoodRepository neighborhoodRepository;

    public NeighborhoodController(NeighborhoodRepository neighborhoodRepository) {
        this.neighborhoodRepository = neighborhoodRepository;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Neighborhood> create(@RequestBody @Valid NeighborhoodCreateRequest neighborhoodPostRequest) {
        var n = this.neighborhoodRepository.save(NeighborhoodCreateRequest.toNeighborhood(neighborhoodPostRequest));
        var uri = URI.create("/neighborhood/" + n.getId());
        return ResponseEntity.created(uri).body(n);
    }

    @GetMapping
    public ResponseEntity<List<Neighborhood>> listAll() {
        return ResponseEntity.ok(this.neighborhoodRepository.findAll());
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable("id") Long id) throws EntityNotFoundException{
         return  ResponseEntity.ok(this.neighborhoodRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Neighborhood.class)));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<?> updateById(@RequestBody @Valid NeighborhoodUpdateRequest neighborhoodPatchRequest, @PathVariable("id") Long id) throws EntityNotFoundException {
        var savedEntity = this.neighborhoodRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Neighborhood.class));

        if (neighborhoodPatchRequest.city() != null) {
            savedEntity.setCity(neighborhoodPatchRequest.city());
            savedEntity.setComplement(neighborhoodPatchRequest.complement());
            this.neighborhoodRepository.save(savedEntity);
            return ResponseEntity.noContent().build();
        }
        savedEntity.setComplement(neighborhoodPatchRequest.complement());
        this.neighborhoodRepository.save(savedEntity);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<?> deleteById(@PathVariable Long id) {
        this.neighborhoodRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
