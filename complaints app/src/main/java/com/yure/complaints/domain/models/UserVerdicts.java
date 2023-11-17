package com.yure.complaints.domain.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class UserVerdicts {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;
    @Enumerated(EnumType.STRING)
    private ComplaintStatus previousStatus;
    @Enumerated(EnumType.STRING)
    private ComplaintStatus status;
    @Column(columnDefinition = "TEXT")
    private String description;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
