package com.example.demo.repository;



import com.example.demo.dto.CityLocate;
import com.example.demo.entity.Employee;
import jakarta.transaction.Transactional;
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
  @Query(value = "select location_id,city,street_address from locations;", nativeQuery = true)
    List<CityLocate> findCities();

  @Transactional
  @Modifying
  @Query(value = "UPDATE departments SET location_id= ?1 WHERE department_id= ?2;", nativeQuery = true)
    void actualizarParte1(int location_id, int department_id);

  @Transactional
  @Modifying
  @Query(value = "UPDATE locations SET postal_code = ?1 WHERE location_id= ?2;", nativeQuery = true)
  void actualizarParte2(String postal_code,int location_id);


}
