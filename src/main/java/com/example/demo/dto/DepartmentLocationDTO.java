package com.example.tareaclase3.dto;

public class DepartmentLocationDTO {

    private String country;
    private String city;
    private Long departmentCount;

    public DepartmentLocationDTO(String country, String city, Long departmentCount) {
        this.country = country;
        this.city = city;
        this.departmentCount = departmentCount;
    }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public Long getDepartmentCount() { return departmentCount; }
    public void setDepartmentCount(Long departmentCount) { this.departmentCount = departmentCount; }
}
