package com.angelcm.listviewactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.angelcm.listviewactivity.models.NotaModel;
import com.angelcm.listviewactivity.operations.NotaOperations;
import java.util.ArrayList;

public class NotasActivity extends AppCompatActivity {

    private ListView lv_notas_contenido;
    private NotaOperations notaOperations;
    private ArrayList<NotaModel> list;
    private ArrayList<String> listString;
    private ArrayAdapter<String> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        lv_notas_contenido = findViewById(R.id.lv_notas_contenido);
        notaOperations = new NotaOperations(getApplicationContext());

        listString = notaOperations.list();

        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listString);
        lv_notas_contenido.setAdapter(itemsAdapter);

        /*

        for(int i=0; i < list.size(); i++){
            consolidadoMostrar += list.get(i) + "\n ---------------- \n\n";
        }

        tv_notas_contenido.setText(consolidadoMostrar);

         */
    }
}