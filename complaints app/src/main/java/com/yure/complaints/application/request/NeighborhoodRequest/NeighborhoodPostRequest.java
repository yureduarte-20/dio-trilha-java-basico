package com.yure.complaints.application.request.NeighborhoodRequest;

import com.yure.complaints.domain.models.Neighborhood;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;

public record NeighborhoodPostRequest(
        @NotBlank
        String city,
         String complement
) {
    public static Neighborhood toNeighborhood(NeighborhoodPostRequest neighborhoodPostRequest){
        var n = new Neighborhood();
        n.setCity(neighborhoodPostRequest.city);
        n.setComplement(neighborhoodPostRequest.complement);
        return n;
    }
}
