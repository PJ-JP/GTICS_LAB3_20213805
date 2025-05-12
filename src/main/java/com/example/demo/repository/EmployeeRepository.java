package com.example.demo.repository;



import com.example.demo.dto.ReportDTO;
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

    @Query(value = "select concat(employees.first_name,' ',employees.last_name) as empleado,jobs.min_salary,jobs.max_salary,jobs.job_id,nombre,employees.salary,promedio from employees, jobs\n" +
            "inner join (select concat(first_name,' ',last_name) as nombre,job_id as job_id_new,promedio from employees \n" +
            "inner join (select job_id as new_job_id, max(salary) as max_salary, avg(salary) as promedio from employees group by job_id) subQuery \n" +
            "on ((employees.salary=subQuery.max_salary) and employees.job_id=subQuery.new_job_id)) subQ\n" +
            "on((jobs.job_id=subQ.job_id_new)) where employees.job_id=jobs.job_id;",nativeQuery = true)
    List<ReportDTO> findReport();

    @Query(value = "select concat(employees.first_name,' ',employees.last_name) as empleado,jobs.min_salary,jobs.max_salary,jobs.job_id,nombre,employees.salary,promedio from employees, jobs\n" +
            "inner join (select concat(first_name,' ',last_name) as nombre,job_id as job_id_new,promedio from employees \n" +
            "inner join (select job_id as new_job_id, max(salary) as max_salary, avg(salary) as promedio from employees group by job_id) subQuery \n" +
            "on ((employees.salary=subQuery.max_salary) and employees.job_id=subQuery.new_job_id)) subQ\n" +
            "on((jobs.job_id=subQ.job_id_new)) where employees.job_id=jobs.job_id and lower(concat(employees.first_name,' ',employees.last_name)) like %?1%;",nativeQuery = true)
    List<ReportDTO> findReportSearch(@Param("search") String search);
}
