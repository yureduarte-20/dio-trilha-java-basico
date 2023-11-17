package com.yure.complaints.application.request.neighborhood;

import com.yure.complaints.domain.models.Neighborhood;
import org.hibernate.validator.constraints.Length;

public record NeighborhoodUpdateRequest(
        @Length(min = 3)
        String city,
        String complement
) {
    public static Neighborhood toNeighborhood(NeighborhoodUpdateRequest neighborhoodPostRequest){
        var n = new Neighborhood();
        n.setCity(neighborhoodPostRequest.city);
        n.setComplement(neighborhoodPostRequest.complement);
        return n;
    }
}
