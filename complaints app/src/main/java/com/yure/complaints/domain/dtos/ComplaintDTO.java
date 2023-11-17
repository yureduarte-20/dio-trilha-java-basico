package com.yure.complaints.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yure.complaints.domain.models.Category;
import com.yure.complaints.domain.models.Complaint;
import com.yure.complaints.domain.models.ComplaintStatus;
import com.yure.complaints.domain.models.Neighborhood;

public record ComplaintDTO(
        Long id,
        String description,
        ComplaintStatus status,
       @JsonProperty("user") UserDTO userDTO,
        Category category,
        Neighborhood neighborhood
) {
    public static ComplaintDTO fromComplaint(Complaint complaint) {
        return new ComplaintDTO(complaint.getId(), complaint.getDescription(),
                complaint.getComplaintStatus(),
                UserDTO.fromUser(complaint.getUser()),
                complaint.getCategory(),
                complaint.getNeighborhood()
        );
    }
}
