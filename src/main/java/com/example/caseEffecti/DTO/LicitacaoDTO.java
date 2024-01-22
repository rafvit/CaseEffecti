package com.example.caseEffecti.DTO;

public class LicitacaoDTO {
    private Integer id;
    private String licitacao;
    private boolean visto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLicitacao() {
        return licitacao;
    }

    public void setLicitacao(String licitacao) {
        this.licitacao = licitacao;
    }

    public boolean isVisto() {
        return visto;
    }

    public void setVisto(boolean visto) {
        this.visto = visto;
    }
}