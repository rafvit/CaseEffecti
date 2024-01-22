package com.example.caseEffecti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.example.caseEffecti.DTO.LicitacaoDTO;
import com.example.caseEffecti.model.LicitacoesModel;
import com.example.caseEffecti.repository.LicitacoesRepository;
import com.example.caseEffecti.service.LicitacoesService;

@RestController
public class LicitacoesController {

    @Autowired
    LicitacoesService service;

    @Autowired
    LicitacoesRepository repository;


    @RequestMapping(value = "/helloworld", method = RequestMethod.GET)
    public String getHelloWorld() {
        return "Hello World";
    }

    @RequestMapping(value = "/licitacao", method = RequestMethod.GET)
    public String getLicitacoes() {

        try {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<LicitacaoDTO> licitacoes = service.convertToDTO();
        String indented = objectMapper.writeValueAsString(licitacoes);

        return indented;

         } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Erro ao processar JSON";
        }
    }

    @RequestMapping(value = "/licitacoes/{id}/visto", method = RequestMethod.PUT)
    public ResponseEntity<String> updateVisto(@PathVariable Integer id) {
        Optional<LicitacoesModel> licitacaoOptional = repository.findById(id);
        
        if (licitacaoOptional.isPresent()) {
            LicitacoesModel licitacao = licitacaoOptional.get();
            if(licitacao.getVisto()==false){
            licitacao.setVisto(true);
            repository.save(licitacao);
            return ResponseEntity.ok("Visto atualizado com sucesso!");

            }
            else if(licitacao.getVisto()==true){
                
                    licitacao.setVisto(false);
                    repository.save(licitacao);
                    return ResponseEntity.ok("Visto atualizado com sucesso!");
            }
            else {
                return ResponseEntity.notFound().build();
            }
        } 
        else {
            return ResponseEntity.notFound().build();
        }
    
    }
}