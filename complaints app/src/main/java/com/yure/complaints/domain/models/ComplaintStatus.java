package com.yure.complaints.domain.models;

public enum ComplaintStatus {
    PENDING("PENDING"),
    IN_REVIEW("IN_REVIEW"),
    IN_PROGRESS("IN_PROGRESS"),
    REFUSED("REFUSED"),
    RESOLVED("RESOLVED");

    ComplaintStatus(String status){}
}
