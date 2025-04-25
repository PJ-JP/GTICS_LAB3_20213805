package com.example.tareaclase3.dto;

import java.time.LocalDateTime;

public class JobReportDTO {
    private String firstName;
    private String lastName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String jobTitle;

    public JobReportDTO(String firstName, String lastName, LocalDateTime startDate, LocalDateTime endDate, String jobTitle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.jobTitle = jobTitle;
    }

    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public LocalDateTime getStartDate() { return startDate; }
    public LocalDateTime getEndDate() { return endDate; }
    public String getJobTitle() { return jobTitle; }
}
