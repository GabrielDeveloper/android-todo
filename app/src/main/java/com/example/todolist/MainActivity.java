package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lista;
    Intent intent;
    private Item_DAO dao ;
    private String[] items;
    private long[] idItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = (ListView) findViewById(R.id.lista);
        setTitle("Gerenciador de Items");
        dao = new Item_DAO(this);
        dao.open();
        lista.setOnItemClickListener(this);
    }
    @Override
    protected void onResume(){
        dao.open();
        super.onResume();
        List<Item> listaItems = dao.getAll();
        items = new String[listaItems.size()];
        idItems = new long[listaItems.size()];
        int i =0;
        Iterator<Item> iterator = listaItems.iterator();
        while (iterator.hasNext()) {
            Item aux = new Item();
            aux = (Item) iterator.next();
            items[i] = aux.textoLista();
            idItems[i] = aux.getId();
            i++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items);
        lista.setAdapter(adapter);
    }
    @Override
    protected void onPause(){
        dao.close();
        super.onPause();
    }
    public void onItemClick(AdapterView<?> parent, View view, int position, long ident){
        long id = idItems[position];
        intent = new Intent(getApplicationContext(), ItemForm.class);
        intent.putExtra("acao", 0);
        intent.putExtra("id", id);
        startActivity(intent);
    }
    public void incluirItem(View v){
        intent = new Intent(getApplicationContext(), ItemForm.class);

        intent.putExtra("acao", -1);
        intent.putExtra("id", 0L);

        startActivity(intent);
    }
    public void sair(View v){
        finish();
    }
}