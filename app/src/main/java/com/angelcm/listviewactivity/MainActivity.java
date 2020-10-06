package com.angelcm.listviewactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.angelcm.listviewactivity.models.NotaModel;
import com.angelcm.listviewactivity.operations.NotaOperations;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private NotaModel model;
    private NotaOperations operations;
    private TextView uno;
    private EditText et_main_titulo, et_main_nota;
    private Button btn_main_guardar, btn_main_ver_notas;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uno = findViewById(R.id.uno);
        et_main_titulo = findViewById(R.id.et_main_titulo);
        et_main_nota = findViewById(R.id.et_main_nota);
        btn_main_guardar = findViewById(R.id.btn_main_guardar);
        btn_main_ver_notas = findViewById(R.id.btn_main_ver_notas);


        btn_main_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ) {

                String titulo = et_main_titulo.getText().toString();
                String contenido = et_main_nota.getText().toString();

                operations = new NotaOperations(MainActivity.this);

                model = new NotaModel(titulo, contenido);
                //operations.insert(model);
                int r = operations.insert(model);
                if(r>0){
                    et_main_titulo.setText("");
                    et_main_nota.setText("");
                    Toast.makeText(MainActivity.this, "Guardado correctamente", Toast.LENGTH_SHORT).show();
                    //uno.setText(String.valueOf(r));
                }else{
                    Toast.makeText(MainActivity.this, "No se guardo correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_main_ver_notas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_notas = new Intent(MainActivity.this, NotasActivity.class);
                startActivity(intent_notas);
            }
        });
        /*
        String consolidadoMostrar = "";
        list = operations.list();

        for(int i=0; i < list.size(); i++){
            consolidadoMostrar += list.get(i) + "\n ---------------- \n\n";
        }

        uno.setText(consolidadoMostrar);
        */

    }
}