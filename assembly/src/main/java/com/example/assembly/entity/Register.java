package com.example.assembly.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "register")
public class Register {

    @Id
    @Column(name = "register")
    public String registerId;

    @Column(name = "value")
    private int value;
}
