package com.vidroid.com.br.apps.listadetarefas.activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.vidroid.com.br.apps.listadetarefas.R;
import com.vidroid.com.br.apps.listadetarefas.helper.TarefaDAO;
import com.vidroid.com.br.apps.listadetarefas.model.Tarefa;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText editTarefa;
    private Tarefa tarefaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);
        editTarefa = findViewById(R.id.editTextAdicionarTarefa);

        //Recuperar tarefa caso seja edição
        tarefaSelecionada = ( Tarefa ) getIntent().getSerializableExtra("tarefaSelecionada");
        if (tarefaSelecionada != null){
            editTarefa.setText(tarefaSelecionada.getNomeTarefa());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

        switch (item.getItemId()) {
            case R.id.menu_adicionar_item: {
                //Salvar no banco de dados
                if(tarefaSelecionada != null){ // editar

                    String tarefaSalva = editTarefa.getText().toString();
                    if(!tarefaSalva.isEmpty()){
                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa(tarefaSalva);
                        tarefa.setId(tarefaSelecionada.getId());

                        //Atualizar no banco de dados
                        if(tarefaDAO.atualizar(tarefa)){
                            finish();
                        }else{
                            editTarefa.setError("Insira uma tarefa ou cancele no botão Voltar");
                        }
                    }

                }else{ // salvar
                    String tarefaSalva = editTarefa.getText().toString();

                    if(!tarefaSalva.isEmpty()){
                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa(tarefaSalva);
                        tarefaDAO.salvar(tarefa);
                        finish();
                    }
                    else{
                        editTarefa.setError("Insira uma tarefa ou cancele no botão Voltar");
                    }
                }

                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
