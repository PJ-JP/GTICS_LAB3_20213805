package com.example.tareaclase3.repository;


import com.example.tareaclase3.dto.JobReportProjection;
import com.example.tareaclase3.entity.Employee;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e " +
            "WHERE LOWER(e.firstName) LIKE %:search% " +
            "OR LOWER(e.lastName) LIKE %:search% " +
            "OR LOWER(e.job.jobTitle) LIKE %:search% " +
            "OR LOWER(e.department.departmentName) LIKE %:search% " +
            "OR LOWER(e.department.location.city) LIKE %:search%")
    List<Employee> findBySearch(@Param("search") String search);

    // Para reporte de empleados con salario > 15000
    @Query("SELECT e FROM Employee e WHERE e.salary > :amount")
    List<Employee> findEmployeesWithSalaryGreaterThan(@Param("amount") double amount);

    // Para reporte de gerentes con experiencia > 5 años
    @Query(value = """
        SELECT DISTINCT e.* FROM employees e
        JOIN job_history jh ON e.employee_id = jh.employee_id
        WHERE DATEDIFF(CURDATE(), jh.start_date) > 5 * 365
        AND e.employee_id IN (SELECT manager_id FROM departments)
        """, nativeQuery = true)
    List<Employee> findExperiencedManagers();

    @Query(value = """
    SELECT e.first_name as firstName, e.last_name as lastName,
           jh.start_date as startDate, jh.end_date as endDate,
           j.job_title as jobTitle
    FROM employees e
    JOIN job_history jh ON e.employee_id = jh.employee_id
    JOIN jobs j ON jh.job_id = j.job_id
    WHERE e.salary > :salary
    """, nativeQuery = true)
    List<JobReportProjection> findWorkHistoryOfEmployeesWithHighSalary(@Param("salary") double salary);


}
