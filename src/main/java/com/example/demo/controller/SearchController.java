package com.example.tareaclase3.controller;

import com.example.tareaclase3.repository.EmployeeRepository;
import com.example.tareaclase3.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private DepartmentRepository departmentRepo;

    @GetMapping
    public String index() {
        return "search/index";
    }

    @GetMapping("/highsalary")
    public String employeesWithHighSalary(Model model) {
        model.addAttribute("list", employeeRepo.findEmployeesWithSalaryGreaterThan(15000));
        return "search/highsalary";
    }



    @GetMapping("/experienced-managers")
    public String experiencedManagers(Model model) {
        model.addAttribute("list", employeeRepo.findExperiencedManagers());
        return "search/experienced_managers";
    }
    @GetMapping("/ejer4/trabajos-sueldo-alto")
    public String trabajosDeEmpleadosConSueldoAlto(Model model) {
        model.addAttribute("lista", employeeRepo.findWorkHistoryOfEmployeesWithHighSalary(15000));
        return "search/ej4_trabajos_sueldo_alto";
    }


    @GetMapping("/ejer4/departamentos-por-ubicacion")
    public String departamentosPorUbicacion(Model model) {
        model.addAttribute("lista", departmentRepo.reportDepartmentsByCountryAndCity());
        return "search/ej4_departamentos_ubicacion";
    }

    @GetMapping("/ejer4/gerentes-experimentados")
    public String gerentesConExperiencia(Model model) {
        model.addAttribute("lista", departmentRepo.reportExperiencedManagers(5 * 365));
        return "search/ej4_gerentes_experimentados";
    }
}
