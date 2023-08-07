package com.example.sahibinden.model.entity;

import com.example.sahibinden.model.Marka;
import com.example.sahibinden.model.Model;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Table(name = "Model")
public class ModelEntity extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @ManyToOne
    private MarkaEntity marka;



}