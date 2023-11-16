package com.yure.complaints.application.controllers;

import com.yure.complaints.application.request.NeighborhoodRequest.NeighborhoodPatchRequest;
import com.yure.complaints.application.request.NeighborhoodRequest.NeighborhoodPostRequest;
import com.yure.complaints.application.response.EntityNotFoundResponse;
import com.yure.complaints.domain.models.Neighborhood;
import com.yure.complaints.domain.repositories.NeighborhoodRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Neighborhood> create(@RequestBody @Valid NeighborhoodPostRequest neighborhoodPostRequest) {
        var n = this.neighborhoodRepository.save(NeighborhoodPostRequest.toNeighborhood(neighborhoodPostRequest));
        var uri = URI.create("/neighborhood/" + n.getId());
        return ResponseEntity.created(uri).body(n);
    }

    @GetMapping
    public ResponseEntity<List<Neighborhood>> listAll() {
        return ResponseEntity.ok(this.neighborhoodRepository.findAll());
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable("id") Long id) {
        var n = this.neighborhoodRepository.findById(id);
        if (n.isPresent()) return ResponseEntity.ok(n.get());
        else return ResponseEntity.unprocessableEntity()
                .body(new EntityNotFoundResponse("Entidade com ID " + id + " não encontrada", Neighborhood.class.getName()));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<?> updateById(@RequestBody @Valid NeighborhoodPatchRequest neighborhoodPatchRequest, @PathVariable("id") Long id) {
        var savedEntity = this.neighborhoodRepository.findById(id);
        if (savedEntity.isEmpty())
            return ResponseEntity.unprocessableEntity().body(new EntityNotFoundResponse("Entidade não encontrada", Neighborhood.class.getName()));
        if (neighborhoodPatchRequest.city() != null) {
            savedEntity.get().setCity(neighborhoodPatchRequest.city());
            savedEntity.get().setComplement(neighborhoodPatchRequest.complement());
            this.neighborhoodRepository.save(savedEntity.get());
            return ResponseEntity.noContent().build();
        }
        savedEntity.get().setComplement(neighborhoodPatchRequest.complement());
        this.neighborhoodRepository.save(savedEntity.get());
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
