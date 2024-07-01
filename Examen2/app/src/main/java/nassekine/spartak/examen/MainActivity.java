package nassekine.spartak.examen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

import nassekine.spartak.examen.dataBase.DbHelper;

public class MainActivity extends AppCompatActivity{

    private Context contexto = this;
    public static ArrayList<Participante> participantes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper dbHelper = new DbHelper(contexto);// objeto helper para la base de datos
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // Abrimos la base de datos en modo escritura


        //Si la base de datos esta vacia ejecuta el if, si no, no
        participantes=dbHelper.getAllParticipantes();

        if(participantes.size()==0){//primera ejecucion guarda todos los participantes creados
            DatosParticipantes datos = new DatosParticipantes();
            participantes= datos.getParticipantes();

            for (Participante p : participantes){
                dbHelper.AddParticipante(p);//se guardan los participantes en la base de datos
            }
            Log.d("Cargo particinates", "Participantes nuevos agregados");

        }






    }

}
