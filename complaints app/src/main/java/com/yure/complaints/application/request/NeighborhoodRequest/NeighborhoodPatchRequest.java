package com.yure.complaints.application.request.NeighborhoodRequest;

import com.yure.complaints.domain.models.Neighborhood;
import org.hibernate.validator.constraints.Length;

public record NeighborhoodPatchRequest(
        @Length(min = 3)
        String city,
        String complement
) {
    public static Neighborhood toNeighborhood(NeighborhoodPatchRequest neighborhoodPostRequest){
        var n = new Neighborhood();
        n.setCity(neighborhoodPostRequest.city);
        n.setComplement(neighborhoodPostRequest.complement);
        return n;
    }
}
