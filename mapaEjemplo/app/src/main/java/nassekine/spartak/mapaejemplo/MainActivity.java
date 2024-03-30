package nassekine.spartak.mapaejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etLatitud, etLongitud;

    Button btnCargarMapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLatitud =  findViewById(R.id.tvLatitud);
        etLongitud = findViewById(R.id.tvLongitud);

        btnCargarMapa = findViewById(R.id.btnCargarMapa);

        btnCargarMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cargarMapa();
            }
        });

    }

    private void cargarMapa() {

        double longitud,latitud;

        latitud=Double.parseDouble(etLatitud.getText().toString());
        longitud=Double.parseDouble(etLongitud.getText().toString());

        MapsActivity.latutud= latitud;
        MapsActivity.longitud= longitud;

        Intent i=new Intent(this, MapsActivity.class);
        startActivity(i);

    }
}