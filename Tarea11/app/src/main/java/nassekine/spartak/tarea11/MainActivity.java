package nassekine.spartak.tarea11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private TextView tv1;
    private RequestQueue requestQueue;// cola de solicitutes autogestionada.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btnRequest);
        tv1 = findViewById(R.id.tv1);
        requestQueue = Volley.newRequestQueue(this);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv1.setText("");//limpia el textview tras cada pulsacion el el boton

                String url="https://alvarogonzalezsotillo.github.io/apuntes-clase/personas.json";

                //JsonArrayRequest con sus 5 argumentos.
                //Alternativas Gson o Moshi, serializan en objetos directamete
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {

                                for(int i=0;i<response.length();i++){

                                    try {

                                        JSONObject jsonObject = new JSONObject(response.get(i).toString());
                                        String nombre = jsonObject.getString("nombre");
                                        int edad = Integer.parseInt(jsonObject.getString("edad"));

                                        if(i==0){//para el primer registro, recorta todo lo que no sea el nombre

                                            int espacioTrasElNombre = nombre.indexOf(" ");

                                            if(espacioTrasElNombre !=-1){//si hay espacio en el nombre

                                                nombre = nombre.substring(0,espacioTrasElNombre);//recorta desde posicion 0 hasta el espacio

                                                tv1.append(nombre+" " + jsonObject.getString("apellidos")+"\n");
                                                tv1.append("Edad: "+jsonObject.getString("edad")+"\n\n");

                                            }

                                        } else if (edad>120) {//para eliminar el ultimo registro

                                            Toast.makeText(MainActivity.this,"Registro erroneo en la posición nº:"+ i, Toast.LENGTH_LONG).show();

                                        } else{

                                            tv1.append(jsonObject.getString("nombre")+" "+ jsonObject.getString("apellidos")+"\n");
                                            tv1.append("Edad: "+jsonObject.getString("edad")+"\n\n");

                                        }

                                    } catch (JSONException e) {

                                        Toast.makeText(MainActivity.this,"error al cargar el Json", Toast.LENGTH_SHORT).show();
                                        throw new RuntimeException(e);

                                    }

                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this,"error al cargar el Json desde la url", Toast.LENGTH_SHORT).show();
                    }
                });

                requestQueue.add(jsonArrayRequest);// agrego a la cola la peticion sin bloqueo del hilo principal.
            }
        });

    }
}