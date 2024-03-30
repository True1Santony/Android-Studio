package nassekine.spartak.tarea12x;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText edBusquedaLocalidad;

    private TextView tvLatitud, tvLongitud, tvCodigoPais, tvPruebas;

    private Button btnBuscar, btnMostrarMapa;
    private RequestQueue requestQueue;
    private String url;
    private Ciudad ciudadAmostrar=null;

    ArrayList<Ciudad> arrayCiudades;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edBusquedaLocalidad=findViewById(R.id.edBusquedaLocalidad);
        tvLongitud=findViewById(R.id.tvLongitud);
        tvLatitud=findViewById(R.id.tvLatitud);
        tvCodigoPais=findViewById(R.id.tvCodigoPais);
        btnBuscar=findViewById(R.id.btnBuscar);
        btnMostrarMapa=findViewById(R.id.btnMostrarMapa);

        tvPruebas=findViewById(R.id.tvprueba);

        arrayCiudades=new ArrayList<>();

        requestQueue= Volley.newRequestQueue(this);
        url="https://alvarogonzalezsotillo.github.io/apuntes-clase/city.list.json";

        //cargo el jason en un array de objetos

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0;i<response.length();i++){

                    try {
                        //se convierte a string in elemento response(JsonObject) para volver a construir un JsonObject a partir de un String(por contexto de volley)
                        JSONObject jsonObject = new JSONObject(response.get(i).toString());

                        JSONObject coordObject = jsonObject.getJSONObject("coord");

                        arrayCiudades.add(new Ciudad(
                                jsonObject.getInt("id"),
                                jsonObject.getString("name"),
                                jsonObject.getString("country"),
                                coordObject.getDouble("lon"),
                                coordObject.getDouble("lat")
                        ));



                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }


                //mostrarCiudadesEnTextView();
                //imprime cuantas ciudades ha obtenido
                Toast.makeText(MainActivity.this,"Puede consultar "+ arrayCiudades.size()+ " ciudades",Toast.LENGTH_SHORT).show();

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });
        requestQueue.add(jsonArrayRequest);//con esto es cuando se ejecuta realmente el jsonArrayRequest y su contenido.

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ciudadBuscada = edBusquedaLocalidad.getText().toString();

                for (Ciudad ciudad : arrayCiudades) {

                    if(ciudad.getName().toLowerCase().contains(ciudadBuscada.toLowerCase())){
                        tvLatitud.setText("Latitud: "+String.valueOf(ciudad.getLat()));
                        tvLongitud.setText("Longitud: "+String.valueOf(ciudad.getLon()));
                        tvCodigoPais.setText("Código de pais: "+ciudad.getCountry());

                        ciudadAmostrar=new Ciudad(ciudad);

                        Toast.makeText(MainActivity.this,"Mostrando datos de:\n"+ ciudad.getName(),Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });

        btnMostrarMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!arrayCiudades.isEmpty() && ciudadAmostrar!=null) {

                   // Toast.makeText(MainActivity.this, "se envia: "+ciudadAmostrar.getLon()+" "+ciudadAmostrar.getLat(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    intent.putExtra("longitud", ciudadAmostrar.getLon());
                    intent.putExtra("latitud", ciudadAmostrar.getLat());
                    startActivity(intent);
                } else {
                    // caso en el que el array de ciudades está vacío o no se ha escogido una ciudad
                    Toast.makeText(MainActivity.this, "INTRODUZCA UNA CIUDAD:\nEj: Torrejon", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
   /* private void mostrarCiudadesEnTextView() {
        StringBuilder sb = new StringBuilder();
        for (Ciudad ciudad : arrayCiudades) {
            sb.append(ciudad.toString()).append("\n");
        }
        tvPruebas.setText(sb.toString());
    }*/

    //pendiente implemetar limiar datos tras dar a mostrar mapa los textview y el Object Ciudad.
    //ampliar a un Listwiew, como en el ejemplo de la unidad(como una opcion).
}