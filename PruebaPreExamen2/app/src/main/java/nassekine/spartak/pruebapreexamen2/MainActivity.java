package nassekine.spartak.pruebapreexamen2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Item> peliculas;
    private static final String API_KEY = "cdce22f8e7ebd0a03760e2c0b1584862";
    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/upcoming?api_key=";
    private static final String IMAGE_URL = "https://image.tmdb.org/t/p/w500";
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPrincipal = findViewById(R.id.btnPrincipal);
        Button btnFavoritos = findViewById(R.id.btnFavoritos);

        peliculas= cargaArrayItems();

        mostrarContenidoSharedPreferences(context);
    }

    public void borradSharedPreferences(View view){

        SharedPreferences sharedPreferences = context.getSharedPreferences("pelis_favoritas", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear(); // Borra todo el contenido de SharedPreferences
        editor.apply(); // Aplica los cambios


        for(Item i : peliculas){  i.setFavorito(false);  }//pone a folse todas las peliculas

        }


    public void seleccionaFragment(View view){

        Fragment miFragment;

        if(view==findViewById(R.id.btnPrincipal)){

            miFragment=new Fragment_ListViewPrincipal();

        } else if (view==findViewById(R.id.btnFavoritos)) {

            miFragment=new Fragment_ListViewFavoritos();


        }  else {

            miFragment=new Fragment_ListViewPrincipal();

        }

        FragmentTransaction  fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContenedor, miFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    private ArrayList<Item> cargaArrayItems() {

        ArrayList<Item> peliculas= new ArrayList<Item>();

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BASE_URL + API_KEY, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                JSONArray jsonArray = jsonObject.optJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++) {

                    try {

                        JSONObject peliculaObject = jsonArray.optJSONObject(i);

                        String titulo = peliculaObject.getString("title");
                        Log.d("JSON_RESPONSE", titulo);
                        String descripcion = peliculaObject.getString("overview");

                        String rutaImagen = IMAGE_URL + peliculaObject.getString("backdrop_path");

                        String estreno = peliculaObject.getString("release_date");

                        int id = peliculaObject.getInt("id");


                        peliculas.add(new Item( rutaImagen,titulo, descripcion, estreno, id));


                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }


                }
                //cargo el primer fragment una vez finalizada la carga de datos
                FragmentTransaction  fragmentTransaction = getSupportFragmentManager().beginTransaction();
                Fragment_ListViewPrincipal fragmentPrincipal = new Fragment_ListViewPrincipal();
                fragmentTransaction.add(R.id.fragmentContenedor, fragmentPrincipal);
                fragmentTransaction.commit();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        requestQueue.add(jsonObjectRequest);

        return peliculas;

    }


    public void mostrarContenidoSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("pelis_favoritas", Context.MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.d("SharedPreferences", entry.getKey() + ": " + entry.getValue().toString());
        }
    }



}