package com.example.demo.repository;



import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

  @Query("SELECT e FROM Employee e " +
            "WHERE CONCAT(LOWER(e.firstName),' ', LOWER(e.lastName)) LIKE %:search%")
    List<Employee> findByName(@Param("search") String search);

  @Query("SELECT e FROM Employee e " +
          "WHERE LOWER(e.department.departmentName) LIKE %:search%")
  List<Employee> findByDepartment(@Param("search") String search);

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

}
