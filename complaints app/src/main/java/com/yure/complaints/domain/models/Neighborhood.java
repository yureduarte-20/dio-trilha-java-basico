package com.yure.complaints.domain.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "neighborhoods")
public class Neighborhood {
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
    @Column(nullable = false )
    private String city;
    @Column( columnDefinition = "TEXT")
    private String complement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }
}
