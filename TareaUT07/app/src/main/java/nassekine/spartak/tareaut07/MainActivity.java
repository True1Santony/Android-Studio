package nassekine.spartak.tareaut07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int aleatorio;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtContador = findViewById(R.id.txtPuntuacion);
        Button btnComprobar= findViewById(R.id.btnCpmprobar);
        TextView txtIntroducido= findViewById(R.id.txtIntroduce);

        sharedPreferences = getPreferences(MODE_PRIVATE);

        //recupera valor guardado si existe.
        int puntuacionGuardada = sharedPreferences.getInt("puntuacion", 0);
        txtContador.setText(String.valueOf(puntuacionGuardada));

        generaAleatorio();


        btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int numeroIntroducido= Integer.parseInt(txtIntroducido.getText().toString());

                if(aleatorio==numeroIntroducido){
                    int puntuacion =Integer.parseInt(txtContador.getText().toString());
                    puntuacion++;
                    generaAleatorio();
                    txtContador.setText(String.valueOf(puntuacion));
                    // Guarda la puntuación en SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("puntuacion", puntuacion);
                    editor.apply();

                    Toast.makeText(MainActivity.this,"Ha acertado!!! Cambio de numero aleatorio",Toast.LENGTH_SHORT).show();
                } else if (aleatorio>numeroIntroducido) {
                    Toast.makeText(MainActivity.this,"No es. Es mayor.",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"No es. Es menor.",Toast.LENGTH_SHORT).show();
                }
                txtIntroducido.setText("");//reset del campo
            }
        });



    }
    public void generaAleatorio(){

        aleatorio = (int) (Math.random() * 20);

    }
}