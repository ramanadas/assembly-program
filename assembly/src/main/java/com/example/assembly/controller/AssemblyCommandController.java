package com.example.assembly.controller;

import com.example.assembly.service.AssemblyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assembly")
public class AssemblyCommandController {

    @Autowired
    private AssemblyService assemblyService;

    @PostMapping("/execute")
    public String executeCommand(@RequestBody String command){
        return assemblyService.execute(command);
    }

}
