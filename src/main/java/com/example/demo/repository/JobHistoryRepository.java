package com.example.tareaclase3.repository;


import com.example.tareaclase3.entity.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.tareaclase3.entity.JobHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, Integer> {

    //No native
    @Query("SELECT jh FROM JobHistory jh " +
            "WHERE LOWER(jh.employee.firstName) LIKE %:search% " +
            "OR LOWER(jh.employee.lastName) LIKE %:search% " +
            "OR LOWER(jh.job.jobTitle) LIKE %:search% " +
            "OR LOWER(jh.department.departmentName) LIKE %:search%")
    List<JobHistory> findBySearch(@Param("search") String search);

    @Query("SELECT jh FROM JobHistory jh " +
            "WHERE LOWER(jh.employee.firstName) LIKE %:search%")
    List<JobHistory> findBySearchNombre(@Param("search") String search);

    @Query("SELECT jh FROM JobHistory jh " +
            "WHERE LOWER(jh.employee.lastName) LIKE %:search% ")
    List<JobHistory> findBySearchApellido(@Param("search") String search);

    @Query("SELECT jh FROM JobHistory jh " +
            "WHERE LOWER(jh.job.jobTitle) LIKE %:search% ")
    List<JobHistory> findBySearchPuesto(@Param("search") String search);

    @Query("SELECT jh FROM JobHistory jh " +
            "WHERE LOWER(jh.department.departmentName) LIKE %:search%")
    List<JobHistory> findBySearchDepartamento(@Param("search") String search);
}

