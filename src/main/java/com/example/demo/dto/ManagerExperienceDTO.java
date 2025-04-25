package com.example.tareaclase3.dto;

public class ManagerExperienceDTO {

    private String departmentName;
    private String managerFirstName;
    private String managerLastName;
    private Double salary;

    public ManagerExperienceDTO(String departmentName, String managerFirstName, String managerLastName, Double salary) {
        this.departmentName = departmentName;
        this.managerFirstName = managerFirstName;
        this.managerLastName = managerLastName;
        this.salary = salary;
    }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public String getManagerFirstName() { return managerFirstName; }
    public void setManagerFirstName(String managerFirstName) { this.managerFirstName = managerFirstName; }

    public String getManagerLastName() { return managerLastName; }
    public void setManagerLastName(String managerLastName) { this.managerLastName = managerLastName; }

    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }
}
