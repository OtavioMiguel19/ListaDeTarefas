package com.vidroid.com.br.apps.listadetarefas.helper;

import com.vidroid.com.br.apps.listadetarefas.model.Tarefa;

import java.util.List;

/**
 * Created by otavi on 15/02/2018.
 */

public interface iTarefaDAO {

    public boolean salvar(Tarefa tarefa);
    public boolean atualizar(Tarefa tarefa);
    public boolean deletar(Tarefa tarefa);
    public List<Tarefa> listar();

}
