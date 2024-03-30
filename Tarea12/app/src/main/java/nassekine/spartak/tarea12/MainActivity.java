package nassekine.spartak.tarea12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    private TextView tvLatitud, tvLongitud, tvCodigoPais;

    private Button btnBuscar, btnMostrarMapa;
    private RequestQueue requestQueue;
    private String url;

    ArrayList<Ciudades> arrayCiudades;
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

                        arrayCiudades.add(new Ciudades(
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

                //imprime cuantas ciudades ha obtenido
                Toast.makeText(MainActivity.this,"Se ha obtenido"+ arrayCiudades.size(),Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
}