package com.yure.complaints.application.controllers;

import com.yure.complaints.application.request.CategoryCreateRequest;
import com.yure.complaints.domain.models.Category;
import com.yure.complaints.domain.repositories.CategoryRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Operation(summary = "Get All Categories", description = "Get ALl Categories")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/")
    ResponseEntity<List<?>> getAll(){
        return ResponseEntity.ok(categoryRepository.findAll());
    }
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> create(@RequestBody @Valid CategoryCreateRequest categoryCreateRequest){
        var category = new Category();
        category.setName(categoryCreateRequest.name());
        category.setDescription(categoryCreateRequest.description());
        return ResponseEntity.ok(this.categoryRepository.save(category));
    }

}
