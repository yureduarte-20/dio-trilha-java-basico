package com.yure.complaints.domain.models;

import jakarta.persistence.*;

@Entity(name = "complaints")
public class Complaint {

    @Id
    private Long id;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    private String status;
    @ManyToOne(fetch = FetchType.EAGER)
    private  Neighborhood neighborhood;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne
    private Category category;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
