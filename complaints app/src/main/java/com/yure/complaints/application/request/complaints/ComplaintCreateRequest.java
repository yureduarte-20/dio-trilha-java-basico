package com.yure.complaints.application.request.complaints;

import com.yure.complaints.domain.models.Category;
import com.yure.complaints.domain.models.Complaint;
import com.yure.complaints.domain.models.Neighborhood;
import com.yure.complaints.domain.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public record ComplaintCreateRequest(
        @NotBlank
        @Length(min = 10)
        String description,
        @NotNull
        @Positive
        long neighborhoodId,
        @Positive
        @NotNull
        long categoryId
) {
        public static Complaint toComplaint(ComplaintCreateRequest complaintCreateRequest){
                var c = new Complaint( );
                c.setDescription(complaintCreateRequest.description());
                return c;
        }
        public static Complaint toComplaint(ComplaintCreateRequest complaintCreateRequest, User user, Category category, Neighborhood neighborhood){
                var complaint = toComplaint(complaintCreateRequest);
                complaint.setUser(user);
                complaint.setCategory(category);
                complaint.setNeighborhood(neighborhood);
                return complaint;
        }

}
