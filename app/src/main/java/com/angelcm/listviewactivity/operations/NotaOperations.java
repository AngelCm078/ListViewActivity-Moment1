package com.angelcm.listviewactivity.operations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.angelcm.listviewactivity.database.DBHelper;
import com.angelcm.listviewactivity.models.NotaModel;

import java.util.ArrayList;

public class NotaOperations {

    private String DBNAME = "appnotasmyapp.db";
    private int VERSION = 1;
    public final Context context;
    private SQLiteDatabase database;
    private DBHelper helper;
    private NotaModel model;
    private ArrayList<String> list;


    public NotaOperations(Context context) {
        this.context = context;
        helper = new DBHelper(context, DBNAME, null, VERSION);
    }

    protected void openRead(){
        database = helper.getReadableDatabase();
    }
    protected void openWrite(){
        database = helper.getWritableDatabase();
    }

    protected void close(){
        database.close();
    }

    public int insert(NotaModel model){
        ContentValues content = new ContentValues();
        content.put("titulo", model.getTitulo());
        content.put("contenido",model.getContenido());

        openWrite();
        long id = database.insert("nota", null, content);
        close();

        return (int)id;
    }

    public ArrayList<String> list(){
        list = new ArrayList<>();

        openRead();

        Cursor cursor = database.query("nota", null, null, null, null, null, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            String titulo, contenido, consolidado, consolidado2;
            int id;
            do{
                id =  cursor.getInt(cursor.getColumnIndex("id"));
                titulo = cursor.getString(cursor.getColumnIndex("titulo"));
                contenido = cursor.getString(cursor.getColumnIndex("contenido"));

                consolidado = String.valueOf(id) + " - " + titulo + " - " + contenido;
                consolidado2 =String.valueOf(id) + ". Titulo= " + titulo + '\n' + contenido;
                list.add(consolidado2);
            }while(cursor.moveToNext());
        }
        close();
        return list;
    }
}
