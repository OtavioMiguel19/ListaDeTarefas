package com.vidroid.com.br.apps.listadetarefas.model;

import java.io.Serializable;

/**
 * Created by otavi on 15/02/2018.
 */

public class Tarefa implements Serializable {

    private Long id;
    private String nomeTarefa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }
}
