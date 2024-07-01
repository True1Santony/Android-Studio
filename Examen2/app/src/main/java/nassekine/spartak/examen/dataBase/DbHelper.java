package nassekine.spartak.examen.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import nassekine.spartak.examen.Participante;

/**
 * Clase que implementa la base de datos
 */
public class DbHelper extends SQLiteOpenHelper {

    //nombre de la base de datos, el archivo que se va a crear
    private static final String DATABASE_NAME = "examen.db";
    //version de la base de datos, cada version incrementa la base de datos y crea una nueva
    private static final int DATABASE_VERSION = 1;
    //nombre de la tabla
    public static final String TABLE_PARTICIPANTES="participantes";


    //Constructor, dejar solo el contexto y en el super se pasan los parametros el contexto y el nombre de la base de datos y la version
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //metodo que se llama cuando se crea la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {

        /*db.execSQL("CREATE TABLE participantes " +
                "(id INTEGER PRIMARY KEY" +
                ", nombre TEXT" +
                ", imagen TEXT" +
                ", gastosPagados INTEGER)");*/

        db.execSQL("CREATE TABLE participantes (id INTEGER PRIMARY KEY, nombre TEXT, imagen TEXT, gastosPagados INTEGER)");

        Log.i("INFO", "Tabla creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("DROP TABLE IF EXISTS participantes");
        onCreate(db);

    }
    //inserta un participante.
    public void AddParticipante(Participante participante){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id", participante.getId());
        values.put("nombre", participante.getNombre());
        values.put("imagen", participante.getImagen());
        values.put("gastosPagados", participante.getGastosPagados());

        db.insert(TABLE_PARTICIPANTES,null,values);
        db.close();

        Log.i("INFO_INSERTADO", "Participante insertado"+participante.getId());
    }


    public ArrayList<Participante> getAllParticipantes(){
        ArrayList<Participante> participantes = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_PARTICIPANTES;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Participante participante = new Participante();
                participante.setId(cursor.getInt(0));
                participante.setNombre(cursor.getString(1));
                participante.setImagen(cursor.getString(2));
                participante.setGastosPagados(cursor.getInt(3));

                participantes.add(participante);

            }while(cursor.moveToNext());

        }


        return participantes;
    }

    public void borrarParticipante(Participante participante){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PARTICIPANTES, "id="+participante.getId(),null);
        db.close();
        Log.i("INFO_BORRADO", "Participante borrado"+participante.getId());
    }



    public void actualizarParticipante(Participante participanteEditar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id", participanteEditar.getId());
        values.put("nombre", participanteEditar.getNombre());
        values.put("imagen", participanteEditar.getImagen());
        values.put("gastosPagados", participanteEditar.getGastosPagados());

        db.update(TABLE_PARTICIPANTES, values, "id=" + participanteEditar.getId(), null);
        db.close();
        Log.i("INFO_ACTUALIZADO", "Participante actualizado"+participanteEditar.getId());


    }
}
