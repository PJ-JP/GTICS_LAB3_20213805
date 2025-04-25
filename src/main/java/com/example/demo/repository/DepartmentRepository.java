package com.example.tareaclase3.repository;

import com.example.tareaclase3.dto.DepartmentLocationDTO;
import com.example.tareaclase3.dto.DepartmentLocationProjection;
import com.example.tareaclase3.dto.ManagerExperienceDTO;
import com.example.tareaclase3.dto.ManagerExperienceProjection;
import com.example.tareaclase3.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.tareaclase3.entity.Department;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    // Buscar ciudades y países con más de 3 empleados por departamento
    @Query(value = """
    SELECT 
        c.country_name AS country,
        l.city AS city,
        COUNT(e.employee_id) AS departmentCount
    FROM departments d
    JOIN locations l ON d.location_id = l.location_id
    JOIN countries c ON l.country_id = c.country_id
    JOIN employees e ON d.department_id = e.department_id
    GROUP BY c.country_name, l.city
    HAVING COUNT(e.employee_id) > 3
    """, nativeQuery = true)
    List<DepartmentLocationProjection> reportDepartmentsByCountryAndCity();


    @Query(value = """
        SELECT d.department_name AS departmentName,
               e.first_name AS managerFirstName,
               e.last_name AS managerLastName,
               e.salary AS salary
        FROM departments d
        JOIN employees e ON d.manager_id = e.employee_id
        WHERE DATEDIFF(CURDATE(), e.hire_date) > :minDias
        """, nativeQuery = true)
    List<ManagerExperienceProjection> reportExperiencedManagers(@Param("minDias") int minDias);

}
