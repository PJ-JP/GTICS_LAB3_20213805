package com.example.tareaclase3.dto;

import java.time.LocalDateTime;

public interface JobReportProjection {
    String getFirstName();
    String getLastName();
    LocalDateTime getStartDate();
    LocalDateTime getEndDate();
    String getJobTitle();
}
