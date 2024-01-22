package com.example.caseEffecti.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.caseEffecti.model.LicitacoesModel;

@Repository
public interface LicitacoesRepository extends JpaRepository<LicitacoesModel, Integer> {
    
}
