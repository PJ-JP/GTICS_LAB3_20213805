package com.example.demo.controller;


import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepo;


    @GetMapping
    public String listEmployees(@RequestParam(value = "search", required = false) String search, @RequestParam(value = "type", required = false) String type,Model model) {
        List<Employee> employees;
        if(search == null || search.isEmpty() || (!type.equals("department") && !type.equals("name"))) {
            employees = employeeRepo.findAll();
        }
        else{
            if(type.equals("department")){
                employees =employeeRepo.findByDepartment(search.toLowerCase());
            }
            else{
                employees =employeeRepo.findByName(search.toLowerCase());
            }
        }

        model.addAttribute("listaEmpleados", employees);
        return "employee/list";
    }



    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable("id") int id, Model model) {
        Employee employee = employeeRepo.findById(id).orElse(null);
        model.addAttribute("employee", employee);
        /*model.addAttribute("departments", departmentRepo.findAll());
        model.addAttribute("jobs", jobRepo.findAll());*/
        model.addAttribute("managers", employeeRepo.findAll());
        return "employee/form";
    }


    /*@PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee,
                               @RequestParam("job.jobId") String jobId,
                               @RequestParam("department.departmentId") Integer departmentId,
                               @RequestParam("manager.employeeId") Integer managerId) {

        // Buscar las entidades en la base de datos
        if (jobId != null && !jobId.isEmpty()) {
            employee.setJob(jobRepo.findById(jobId).orElse(null));
        }

        if (departmentId != null) {
            employee.setDepartment(departmentRepo.findById(departmentId).orElse(null));
        }

        if (managerId != null) {
            employee.setManager(employeeRepo.findById(managerId).orElse(null));
        }

        employeeRepo.save(employee);
        return "redirect:/employees";
    }*/
}


