package devandroid.daniel.appgaseta.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import devandroid.daniel.appgaseta.model.Combustivel;

public class GasEtaDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "gaseta.db";
    private static final int DB_VERSION = 1;

    Cursor cursor;
    SQLiteDatabase db;

    public GasEtaDB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String sqlTabelaCombustivel = "CREATE TABLE Combustivel (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nomeDoCombustivel TEXT, " +
                "precoDoCombustivel REAL, " +
                "recomendacao TEXT)";

        String sqlTabelaVeiculo = "CREATE TABLE Veiculo (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nomeDoCarro TEXT, " +
                "placaDoCarro TEXT)";

        sqLiteDatabase.execSQL(sqlTabelaCombustivel);
        sqLiteDatabase.execSQL(sqlTabelaVeiculo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // Criar métodos para implementar um
    public void salvarObjeto(String tabela, ContentValues dados){
        db.insert(tabela, null,dados);
    }

    public List<Combustivel> listarDados(){
        List<Combustivel> lista = new ArrayList<>();

        // Representa um registro que está salvo na tabela
        // Combustivel do BD da Aplicação

        Combustivel registro;

        String querySql = "Select * from Combustivel";

        cursor = db.rawQuery(querySql, null);

        if(cursor.moveToFirst()) {

            do {

                registro = new Combustivel();
                registro.setId(cursor.getInt(0));
                registro.setNomeDoCombustivel(cursor.getString(1));
                registro.setPrecoDoCombustivel(cursor.getDouble(2));
                registro.setRecomendacao(cursor.getString(3));

                lista.add(registro);

            } while (cursor.moveToNext());
        } else {

        }

        return lista;
    }

    public void alterarObjeto(String tabela, ContentValues dados){
        // ID do registro a ser alterado
        // Update TABLE set campo = novoDado WHERE id = ?
        int id = dados.getAsInteger("id");

        db.update(tabela, dados, "id=?", new String[]{Integer.toString(id)});
    }

}
