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
    public static final String TABLE_NAME2 = "usuario";
    public static final String TABLE_NAME3 = "casos";

    private SQLiteDatabase bd;
    public static final String COL_1 = "id";
    public static final String COL_2 = "id_usuario";
    public static final String COL_3 = "usuario";
    public static final String COL_4 = "senha";
    public static final String COL_5 = "cidade";
    public static final String COL_6 = "lat";
    public static final String COL_7 = "lng";
    public static final String COL_8 = "tipo";
    private static final String TABLE_LOGIN = "create table login (id integer primary key  not null , " + "  id_usuario int , usuario TEXT , senha TEXT , cidade TEXT , lat REAL, lng REAL, tipo int);";

    //adapter
    public static final String COL_ID = "idUser";
    public static final String COL_NOME = "nomeUser";
    public static final String COL_USER = "userUser";
    public static final String COL_SENHA = "senhaUser";
    public static final String COL_CIDADE = "cidadeUser";
    public static final String COL_LAT = "latUser";
    public static final String COL_LNG = "lngUser";
    public static final String COL_TIPO = "tipoUser";

    private static final String TABLE_USUARIO = "create table usuario (idUser integer primary key  not null , " + " nomeUser TEXT NOT NULL, userUser TEXT NOT NULL, senhaUser TEXT NOT NULL, cidadeUser TEXT NOT NULL, latUser REAL, lngUser REAL, tipoUser int );";


    public static final String COL_IDD = "idDoenca";
    public static final String COL_IDUSERD = "id_usuarioDoenca";
    public static final String COL_TIPOD = "tipoDoenca";
    public static final String COL_NOMED = "nomePessoaDoenca";
    public static final String COL_ENDERECOD = "enderecoDoenca";
    public static final String  COL_DATA = "dataDoenca";
    public static final String COL_LATD = "latDoenca";
    public static final String COL_LNGD = "lngDoenca";

    private static  final String TABLE_CASOS = "create table casos ( idDoenca integer primary key  ,  "+" id_usuarioDoenca int, tipoDoenca TEXT ,  nomePessoaDoenca TEXT , enderecoDoenca TEXT,dataDoenca TEXT, latDoenca REAL, lngDoenca REAL);";


    public BancoDeDados(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);


    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_LOGIN);
        sqLiteDatabase.execSQL(TABLE_USUARIO);
        sqLiteDatabase.execSQL(TABLE_CASOS);


        this.bd = sqLiteDatabase;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME +TABLE_NAME2 + TABLE_NAME3);
        onCreate(db);
    }



    public void insertContact(BancoDeDadosAdapter c) {
        bd = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from login";
        Cursor cursor = bd.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COL_1, count);
        values.put(COL_2, c.getId_usuario());
        values.put(COL_3, c.getUsuario());
        values.put(COL_4, c.getSenha());
        values.put(COL_5, c.getCidade());
        values.put(COL_6, c.getLat());
        values.put(COL_7, c.getLng());
        values.put(COL_8, c.getTipo());
        bd.insert(TABLE_NAME, null, values);
        Log.e("BD1", "Criou uma coluna");

    }

//adapter


    public void insertContactt(BancoDeDadosAdapter c) {
        bd = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from usuario";
        Cursor cursor = bd.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COL_ID, count);
        values.put(COL_NOME, c.getNomeUser());
        values.put(COL_USER, c.getUserUser());
        values.put(COL_SENHA, c.getSenhaUser());
        values.put(COL_CIDADE, c.getCidadeUser());
        values.put(COL_LAT, c.getLatUser());
        values.put(COL_LNG, c.getLngUser());
        values.put(COL_TIPO, c.getTipoUser());
        bd.insert(TABLE_NAME2, null, values);
        Log.e("BD1", "Criou uma coluna");

    }

    public void insertContacttt(BancoDeDadosAdapter c) {
        bd = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from casos";
        Cursor cursor = bd.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COL_IDD, count);
        values.put(COL_IDUSERD, c.getId_usuarioDoenca());
        values.put(COL_TIPOD, c.getTipoDoenca());
        values.put(COL_NOMED, c.getNomePessoaDoenca());
        values.put(COL_ENDERECOD, c.getEnderecoDoenca());
        values.put(COL_DATA, c.getDataDoenca());
        values.put(COL_LATD, c.getLatDoenca());
        values.put(COL_LNGD, c.getLngDoenca());
        bd.insert(TABLE_NAME3, null, values);

        Log.e("BD casos", "Criou uma coluna");
    }
}

