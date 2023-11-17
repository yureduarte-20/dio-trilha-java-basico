package com.yure.complaints.domain.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private  Neighborhood neighborhood;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus complaintStatus = ComplaintStatus.PENDING;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @ManyToOne
    private Category category;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Complaint(Long id, String description,  Neighborhood neighborhood, ComplaintStatus complaintStatus, User user, Category category) {
        this.id = id;
        this.description = description;

        this.neighborhood = neighborhood;
        this.complaintStatus = complaintStatus;
        this.user = user;
        this.category = category;
    }

    public Complaint(String description, Neighborhood neighborhood, ComplaintStatus complaintStatus, User user, Category category) {
        this.description = description;

        this.neighborhood = neighborhood;
        this.complaintStatus = complaintStatus;
        this.user = user;
        this.category = category;
    }

    public Complaint() {

    }

    public String getDescription() {
        return description;
    }
    public ComplaintStatus getComplaintStatus() {
        return complaintStatus;
    }

    public void setComplaintStatus(ComplaintStatus complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    public void setDescription(String description) {
        this.description = description;
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


}
