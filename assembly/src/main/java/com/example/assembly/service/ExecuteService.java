package com.example.assembly.service;

import com.example.assembly.entity.Program;
import com.example.assembly.entity.Register;
import com.example.assembly.repository.ProgramRepository;
import com.example.assembly.repository.RegistarRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExecuteService {
    @Autowired
    private RegistarRepo registarRepo;
    @Autowired
    private ProgramRepository programRepository;

    public String executeCommand(String command) throws RuntimeException {
        String ans = "";
        /*MV REG1,#2000
        MV REG2,#4000
        ADD REG1, REG2
        ADD REG1,600
        SHOW REG
         */
        if(command.startsWith("mv ") || command.startsWith("MV ")){
            String[] temp = command.substring(3).trim().split(",");
            int val;
            if (temp[1].startsWith("#")){
                val = Integer.parseInt(temp[1].substring(1));
                registarRepo.save(new Register(temp[0], val));
                Program program = new Program();
                program.setProgramText(command);
                program.setResult("inserted in to " + temp[0] + " successfully");
                ans = ans.concat(program.getProgramText() + program.getResult());
                programRepository.save(program);
            }else {
                Register register = null;
                try {
                    register = registarRepo.getById(temp[1]);
                    val=register.getValue();
                    registarRepo.save(new Register(temp[0], val));
                    Program program = new Program();
                    program.setProgramText(command);
                    program.setResult("inserted in to " + temp[0] + " successfully");
                    programRepository.save(program);
                    ans = ans.concat(program.getProgramText() + program.getResult());
                } catch (Exception e) {
                    Program program = new Program();
                    program.setProgramText(command);
                    program.setResult("failed");
                    ans = ans.concat(program.getProgramText() + program.getResult());
                    programRepository.save(program);

                }
            }
        }else if(command.startsWith("add ") || command.startsWith("ADD ")){
            String t = command.substring(4);
            String[] temp = t.split(",");
            String t1 = new String(temp[1].trim());
            t1 = t1.trim();
            try{
                int num = Integer.parseInt(t1);
                Register register = registarRepo.getById(temp[0]);
                register.setValue(num);
                registarRepo.save(register);

            }catch (NumberFormatException e){
                Register register1 = registarRepo.getById(temp[0]);
                Register register2 = registarRepo.getById(t1);
                int total = register1.getValue()+register2.getValue();
                register1.setValue(total);
                registarRepo.save(register1);

            }
            Program program = new Program();
            program.setProgramText(command);
            program.setResult("added successfully and saved in "+temp[0]+" register");
            ans = ans.concat(program.getProgramText() + program.getResult());
            programRepository.save(program);
        }else if(command.startsWith("show ") || command.startsWith("SHOW ")){
            String reg = command.substring(5).trim();
            try {
                Register register = registarRepo.getById(reg);
                Program program = new Program();
                program.setProgramText(command);
                program.setResult(reg + " :" + register.getValue());
                ans = ans.concat(program.getProgramText() +" "+ program.getResult());
                programRepository.save(program);
            } catch (Exception e) {
                ans = ans.concat(reg+" not exits");
            }
        }else {
            Program program = new Program();
            program.setProgramText(command);
            program.setResult("failed");
            ans = ans.concat(program.getProgramText() + program.getResult());
            programRepository.save(program);
        }
        return ans;
    }
}
