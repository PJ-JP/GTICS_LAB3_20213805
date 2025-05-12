package com.example.demo.controller;


import com.example.demo.dto.ReportDTO;
import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private EmployeeRepository employeeRepo;


    @GetMapping
    public String listEmployees(@RequestParam(value = "search", required = false) String search,Model model) {
        List<ReportDTO> report;
        if(search == null || search.isEmpty()) {
            report = employeeRepo.findReport();
        }
        else{
            report =employeeRepo.findReportSearch((search.toLowerCase()));
        }

        model.addAttribute("report", report);
        return "report/reportList";
    }
}

