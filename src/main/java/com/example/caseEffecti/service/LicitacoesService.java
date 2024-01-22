package com.example.caseEffecti.service;

import com.example.caseEffecti.DTO.LicitacaoDTO;
import com.example.caseEffecti.model.LicitacoesModel;
import com.example.caseEffecti.repository.LicitacoesRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


@Service
public class LicitacoesService {

    @Autowired
    LicitacoesRepository repository;

        public String getLicitacoes() {
        HtmlPage page = null;

            try (final WebClient webClient = new WebClient()) {
                webClient.getOptions().setCssEnabled(false);
                webClient.getOptions().setJavaScriptEnabled(false);
                page = webClient.getPage("http://comprasnet.gov.br/ConsultaLicitacoes/ConsLicitacaoDia.asp");

            } catch (IOException e) {
                e.printStackTrace();
            }

            HtmlElement book = page.getFirstByXPath("//table[@class=\"tex3\"]"); 
            String licitacao = book.asNormalizedText();
            return licitacao;
    }

    public String[] tratamentoLicitacao(){

        String licitacao = getLicitacoes();

        String[] arrayLicitacoes = licitacao.split("Itens e Download");

        String[] licitacoesCerta = new String[arrayLicitacoes.length -1];

        for(int i=0; i < licitacoesCerta.length; i++){

            licitacoesCerta[i] = arrayLicitacoes[i];
        }

        return licitacoesCerta;
    }

    public void createObj(){
        
        String[] licitacoes = tratamentoLicitacao();

        for(int i=0; i < licitacoes.length; i++){

            LicitacoesModel licitacoesModel = new LicitacoesModel();
            licitacoesModel.setId(i+1);
            licitacoesModel.setLicitacao(licitacoes[i]);
            licitacoesModel.setVisto(false);

           repository.save(licitacoesModel);
        }

    }

    public List<LicitacaoDTO> convertToDTO() {
        List<LicitacaoDTO> dtos = new ArrayList<>();
        List<LicitacoesModel> licitacoes = repository.findAll();

        for (LicitacoesModel licitacao : licitacoes) {
            LicitacaoDTO dto = new LicitacaoDTO();
            dto.setId(licitacao.getId());
            dto.setLicitacao(licitacao.getLicitacao());
            dto.setVisto(licitacao.getVisto());
            dtos.add(dto);
        }
        return dtos;
    }

}