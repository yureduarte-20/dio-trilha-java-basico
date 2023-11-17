package com.yure.complaints.application.controllers;

import com.yure.complaints.application.exceptions.EntityNotFoundException;
import com.yure.complaints.application.request.category.CategoryCreateRequest;
import com.yure.complaints.application.request.category.CategoryUpdateRequest;
import com.yure.complaints.domain.models.Category;
import com.yure.complaints.domain.repositories.CategoryRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/category")
@SecurityRequirement(name = "Bearer Authentication")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Operation(summary = "Get All Categories", description = "Get ALl Categories")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/")
    ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.CREATED)
    ResponseEntity<?> create(@RequestBody @Valid CategoryCreateRequest categoryCreateRequest) {
        var category = new Category();
        category.setName(categoryCreateRequest.name());
        category.setDescription(categoryCreateRequest.description());
        category = this.categoryRepository.save(category);
        var uri = URI.create("/category/" + category.getId());
        return ResponseEntity.created(uri).body(category);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById", description = "Gets specific category by provided id",
            responses = {
                    @ApiResponse(responseCode = "200", description = ""),
                    @ApiResponse(responseCode = "422", description = "Throws when provided id not match in DB")
            }
    )
    ResponseEntity<Category> findById(@PathVariable Long id) throws EntityNotFoundException {
        var cat = this.categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Category.class));
        return ResponseEntity.ok(cat);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<?> updateById(@RequestBody @Valid CategoryUpdateRequest categoryUpdateRequest, @PathVariable Long id) throws EntityNotFoundException {
        var savedCategory = this.categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Category.class));
        if(categoryUpdateRequest.name() != null){
            savedCategory.setName(categoryUpdateRequest.name());
        }
        savedCategory.setDescription(categoryUpdateRequest.description());
        this.categoryRepository.save(savedCategory);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> deleteById(@PathVariable Long id) {
        this.categoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
