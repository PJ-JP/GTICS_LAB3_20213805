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



    @GetMapping("/edit")
    public String editEmployee(@RequestParam(value = "id") int id, Model model) {
        Employee employee = employeeRepo.findById(id).orElse(null);
        model.addAttribute("employee", employee);
        model.addAttribute("cities", employeeRepo.findCities());
        return "employee/editFrm";
    }


    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee,
                               @RequestParam(value = "department_id") int deparment_id,
                               @RequestParam(value = "location_id") int location_id) {

        employeeRepo.actualizarParte1(location_id, deparment_id);
        employeeRepo.actualizarParte2(employee.getDepartment().getLocation().getPostalCode(),location_id);
        return "redirect:/employee";
    }
}


