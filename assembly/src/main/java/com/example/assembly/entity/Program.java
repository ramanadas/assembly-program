package com.example.assembly.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "programs")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "program_text", columnDefinition = "TEXT")
    private String programText;

    @Column(name = "result")
    private String result;
}