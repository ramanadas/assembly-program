package com.example.assembly.repository;

import com.example.assembly.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistarRepo extends JpaRepository<Register, String> {
    @Override
    Register getById(String s);
}
