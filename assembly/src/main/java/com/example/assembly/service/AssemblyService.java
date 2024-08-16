package com.example.assembly.service;

import com.example.assembly.entity.Program;
import com.example.assembly.repository.ProgramRepository;
import com.example.assembly.repository.RegistarRepo;
import com.example.assembly.util.PraseCommand;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Service
public class AssemblyService {

    @Autowired
    private RegistarRepo registarRepo;
    @Autowired
    private PraseCommand praseCommand;
    @Autowired
    private ExecuteService executeService;
    @Autowired
    private ProgramRepository programRepository;

    @Transactional
    public String execute(String input) {
        String res = "";
        boolean ifStarted=false;
        boolean first = true;
        String[] temp={};
        List<String> list = new ArrayList<>();
        try {
            List<String> commands = praseCommand.parse(input);
            for(String command : commands){
                if(command.startsWith("IFGT AND IF")){
                   ifStarted=true;
                   continue;
                }
                if(command.startsWith("ENDIF")){
                    ifStarted=false;
                    continue;
                }
                if(ifStarted){
                    if(first){
                        temp= command.split(" ");
                    }

                    if(registarRepo.getById(temp[1]).getValue()>Integer.parseInt(temp[2])){
                        if(first){
                            first=false;
                        }else {
                            list.add(command);
                        }
                    }
                    first=false;
                    continue;
                }
                list.add(command);

            }
            for(String command: list){
                String s = executeService.executeCommand(command);
                res=res.concat(s +"\n");
            }
        } catch (Exception e) {
            return e.getMessage();
        }


        return res;
    }


}
