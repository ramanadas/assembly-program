package com.example.assembly.service;

import com.example.assembly.entity.Program;
import com.example.assembly.repository.ProgramRepository;
import com.example.assembly.util.PraseCommand;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssemblyService {

    @Autowired
    private PraseCommand praseCommand;
    @Autowired
    private ExecuteService executeService;
    @Autowired
    private ProgramRepository programRepository;

    @Transactional
    public String execute(String input) {
        String res = "";
        try {
            List<String> commands = praseCommand.parse(input);
            for(String command : commands){
                String s = executeService.executeCommand(command);
                res=res.concat(s +"\n");
            }
        } catch (Exception e) {
            return e.getMessage();
        }


        return res;
    }


}
