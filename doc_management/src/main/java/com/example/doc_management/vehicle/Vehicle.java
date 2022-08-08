package com.example.doc_management.vehicle;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "vehicle") @Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String matricule;
    private String papers;

}
