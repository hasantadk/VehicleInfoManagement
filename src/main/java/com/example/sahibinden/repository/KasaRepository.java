package com.example.sahibinden.repository;

import com.example.sahibinden.model.entity.KasaEntity;
import com.example.sahibinden.model.entity.MarkaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KasaRepository extends JpaRepository<KasaEntity, Long> {
}