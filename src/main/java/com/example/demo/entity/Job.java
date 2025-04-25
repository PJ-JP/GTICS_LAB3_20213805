package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @Column(name = "job_id")
    private String id;

    private String jobTitle;
    private Double minSalary;
    private Double maxSalary;

    //@OneToMany(mappedBy = "job")
    //private List<Employee> employees;

    public Job() {}


}
