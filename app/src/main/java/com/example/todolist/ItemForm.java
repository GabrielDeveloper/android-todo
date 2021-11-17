package com.example.todolist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ItemForm extends AppCompatActivity {

        EditText input_task_title, input_task_desription, input_task_priority,
                input_task_user_email, input_task_user;
        Button btn_alterar, btn_excluir;
        private int acao;
        private long id;

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_item_form);
            btn_alterar = (Button) findViewById(R.id.btn_alterar);
            btn_excluir = (Button) findViewById(R.id.btn_excluir);
            input_task_title = (EditText) findViewById(R.id.input_task_title);
            input_task_desription = (EditText) findViewById(R.id.input_task_desription);
            input_task_priority = (EditText) findViewById(R.id.input_task_priority);
            input_task_user_email = (EditText) findViewById(R.id.input_task_user_email);
            input_task_user = (EditText) findViewById(R.id.input_task_user);
            acao = getIntent().getExtras().getInt("acao");
            id = getIntent().getExtras().getLong("id");

            if (acao == -1) {
                setTitle("Incluir Item");
                btn_alterar.setText("Incluir");
                btn_excluir.setEnabled(false);
                input_task_title.setText("Nome do Item");
                input_task_desription.setText("Descrição do Item");
                input_task_priority.setText("1");
                input_task_user_email.setText("Email do usuario");
                input_task_user.setText("Usuario");
            } else {
                setTitle("Alterar ou Excluir Item");
                Item aux = new Item();
                Item_DAO dao = new Item_DAO(this);
                dao.open();
                aux = dao.buscar(id);
                input_task_title.setText(aux.getTitle());
                input_task_desription.setText(aux.getDescription());
                input_task_priority.setText(String.valueOf(aux.getPriority()));
                input_task_user_email.setText(aux.getUserEmail());
                input_task_user.setText(aux.getUser());
                dao.close();
            }
        }

        public void alterarInserir(View v) {
            int priority;
            String title, description, user_email, user;

            title = input_task_title.getText().toString();
            description = input_task_desription.getText().toString();
            user_email = input_task_user_email.getText().toString();
            user = input_task_user.getText().toString();
            priority = Integer.parseInt(input_task_priority.getText().toString());

            Item_DAO dao = new Item_DAO(this);
            dao.open();

            if (acao == -1) {
                dao.inserir(title, description , priority, user_email, user);
            } else {
                dao.alterar( id , title, description, priority, user_email, user);
            }

            dao.close();
            finish();
        }
        public void excluir(View v) {
            if (acao == 0) {
                Item_DAO dao = new Item_DAO(this);
                dao.open();
                dao.apagar(id);
                dao.close();
            }
            finish();
        }

        public void voltar(View v) {
            finish();
        }
}
