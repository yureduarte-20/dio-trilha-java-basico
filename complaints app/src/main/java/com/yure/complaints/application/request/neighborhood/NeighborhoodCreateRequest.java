package com.yure.complaints.application.request.neighborhood;

import com.yure.complaints.domain.models.Neighborhood;
import jakarta.validation.constraints.NotBlank;

public record NeighborhoodCreateRequest(
        @NotBlank
        String city,
         String complement
) {
    public static Neighborhood toNeighborhood(NeighborhoodCreateRequest neighborhoodPostRequest){
        var n = new Neighborhood();
        n.setCity(neighborhoodPostRequest.city);
        n.setComplement(neighborhoodPostRequest.complement);
        return n;
    }
}
