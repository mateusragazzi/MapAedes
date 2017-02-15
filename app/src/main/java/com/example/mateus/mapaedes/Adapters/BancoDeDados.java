package com.example.mateus.mapaedes.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by zazah on 14/02/2017.
 */

public class BancoDeDados extends SQLiteOpenHelper {

    private static final String NOME_BD = "teste";
    private static final int VERSAO_BD = 1;
    public static final String TABLE_NAME = "login";

    private SQLiteDatabase bd;
    public static final String COL_1 = "id";
    public static final String COL_2 = "id_usuario";
    public static final String COL_3 = "usuario";
    public static final String COL_4 = "senha";
    public static final String COL_5 = "cidade";
    public static final String COL_6 = "lat";
    public static final String COL_7 = "lng";
    private static  final String TABLE_LOGIN = "create table login (id integer primary key  not null , "+"  id_usuario int , usuario TEXT , senha TEXT , cidade TEXT , lat REAL, lng REAL);";

    public BancoDeDados(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_LOGIN);
        this.bd= sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertContact(BancoDeDadosAdapter c) {
        bd = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from login";
        Cursor cursor = bd.rawQuery(query, null);
        int count =cursor.getCount();

        values.put(COL_1, count);
        values.put(COL_2, c.getId_usuario());
        values.put(COL_3, c.getUsuario());
        values.put(COL_4, c.getSenha());
        values.put(COL_5, c.getCidade());
        values.put(COL_6, c.getLat());
        values.put(COL_7, c.getLng());
        bd.insert(TABLE_NAME, null, values);
        Log.e("BD1","Criou uma coluna");


    }
}
