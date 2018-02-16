package com.vidroid.com.br.apps.listadetarefas.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.vidroid.com.br.apps.listadetarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by otavi on 15/02/2018.
 */

public class TarefaDAO implements iTarefaDAO {

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public TarefaDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        escreve = dbHelper.getWritableDatabase();
        le = dbHelper.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefa tarefa) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", tarefa.getNomeTarefa());

        try {
            escreve.insert(
                    DBHelper.TABELA_TAREFAS,
                    null,
                    contentValues
            );
            Log.i("INFO", "Sucesso ao salvar tabela! ");
        }catch (Exception e){
            Log.e("INFO", "Erro ao salvar tabela: " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", tarefa.getNomeTarefa());
        try {
            String[] args = {tarefa.getId().toString()};
            escreve.update(
                    DBHelper.TABELA_TAREFAS,
                    contentValues,
                    "id = ?",
                    args
            );
            Log.i("INFO", "Sucesso ao salvar tabela! ");
        }catch (Exception e){
            Log.e("INFO", "Erro ao salvar tabela: " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {

        try {
            String[] args = {tarefa.getId().toString()};
             escreve.delete(
                     DBHelper.TABELA_TAREFAS,
                     "id = ?",
                     args);
            Log.i("INFO", "Sucesso ao remover tabela! ");
        }catch (Exception e){
            Log.e("INFO", "Erro ao remover tabela: " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public List<Tarefa> listar() {

        List<Tarefa> tarefaList = new ArrayList<>();
        String sql = "SELECT * FROM " + DBHelper.TABELA_TAREFAS + " ;";
        Cursor cursor = le.rawQuery(sql, null);

        while (cursor.moveToNext()){
            Tarefa tarefa = new Tarefa();

            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            String nomeTarefa = cursor.getString(cursor.getColumnIndex("nome"));

            tarefa.setId(id);
            tarefa.setNomeTarefa(nomeTarefa);

            tarefaList.add(tarefa);
        }

        return tarefaList;
    }
}
