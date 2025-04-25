package com.example.tareaclase3.controller;

import com.example.tareaclase3.repository.JobHistoryRepository;
import com.example.tareaclase3.entity.JobHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private JobHistoryRepository historyRepo;

    @GetMapping
    public String viewHistory(@RequestParam(value = "search", required = false) String search,@RequestParam(value = "type", required = false) Integer type,  Model model) {
        List<JobHistory> history = new ArrayList<>();
        if(search == null || search.isEmpty() || type == null) {
            history = historyRepo.findAll();
        }
        else{
            switch (type){
                case 0 -> history = historyRepo.findBySearch(search.toLowerCase());
                case 1 -> history = historyRepo.findBySearchNombre(search.toLowerCase());
                case 2 -> history = historyRepo.findBySearchApellido(search.toLowerCase());
                case 3 -> history = historyRepo.findBySearchPuesto(search.toLowerCase());
                case 4 -> history = historyRepo.findBySearchDepartamento(search.toLowerCase());
            }
        }
        model.addAttribute("history", history);
        return "history/list";
    }
}

/*
* //@Autowired
    //private JobHistoryRepository historyRepo;

    final JobHistoryRepository historyRepo;

    public HistoryController(JobHistoryRepository historyRepo){
        this.historyRepo = historyRepo;
    }
* */