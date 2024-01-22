package com.example.caseEffecti.model;

import jakarta.persistence.*;

@Entity
@Table(name = "LICITACAO")
public class LicitacoesModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "LICITACAO", length = 2000)
    private String licitacao;

    @Column(name = "VISTO")
    private Boolean visto;

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

    public Boolean getVisto() {
        return visto;
    }

    public void setVisto(Boolean visto) {
        this.visto = visto;
    }

    @Override
    public String toString() {
        return "\n{ \"id\":\"" + id + "\", \n"
                + " \"licitacao\": \"" + licitacao + "\", \n"
                + "\"visto\":\"" + visto + "\", \n";
    }
}