package com.example.gatos_retrofit_java;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.gatos_retrofit_java.clases.CallBackInterface;
import com.example.gatos_retrofit_java.clases.Cat;
import com.example.gatos_retrofit_java.clases.CatApiResponse;
import com.example.gatos_retrofit_java.clases.CatApiService;
import com.example.gatos_retrofit_java.clases.ZoomOutPageTransformer;
import com.example.gatos_retrofit_java.fragmentos.DetalleFragment;
import com.example.gatos_retrofit_java.fragmentos.FotoFragment;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;


import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/*
import com.android.volley.Request;

import com.android.volley.RequestQueue;
//import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;*/

public class MainActivity extends AppCompatActivity implements CallBackInterface {
    private ViewPager2 viewPager;

    private static final String BASE_URL = "https://api.api-ninjas.com/v1/";
    private static final String API_KEY = "vlxC2zqMN6rINoCf3E+xEw==NFLHiv0KjDRy2ngs";
    private List<Cat> cats;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        context = this;
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Referenciamos el ViewPager que hemos puesto de layout
        viewPager =findViewById(R.id.paginador);
        /* para que la orientación del desplazamiento sea horizontal*/
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
          /*https://developer.android.com/develop/ui/views/animations/screen-slide-2
        Customize the animation using PageTransformer*/
        viewPager.setPageTransformer(new ZoomOutPageTransformer());

      callRetrofit();
       // callVolley();
    }
    @Override
    public void callBackMethod(Cat id) {
        // mostrar el fragment con el detalle que corresponda
        addDetalle(id);

    }
    private void addDetalle(Cat id) {
       // ejemplo de prueba
        // se puede borrar
        Cat exampleCat = new Cat(
                "12 to 16 inches",
                "Southeast Asia",
                "https://api-ninjas.com/images/cats/abyssinian.jpg",
                3,  // familyFriendly
                3,  // shedding
                2,  // generalHealth
                5,  // playfulness
                5,  // childrenFriendly
                3,  // grooming
                5,  // intelligence
                5,  // otherPetsFriendly
                6,  // minWeight
                10, // maxWeight
                9,  // minLifeExpectancy
                15, // maxLifeExpectancy
                "Abyssinian"
        );
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        DetalleFragment detalleFragment = DetalleFragment.newInstance("0", id);
        fragmentTransaction.replace(R.id.fragmentContainer, detalleFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }
/*
private void  callVolley(){
// Crear una instancia de la cola de solicitudes de Volley
    RequestQueue queue = Volley.newRequestQueue(this);
    Response.Listener  listener =new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            //  tratamiento del resultado
            Log.d("mostrar", response.toString());
        }
    };
    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
         // tratamiento del error
            Log.d("mostrar", "error");

        }

    };
    // Crear la solicitud GET
    JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, BASE_URL, null, listener, errorListener){
        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("x-api-key", API_KEY);
            return params;
        }
    };

    queue.add(request);
}
*/
private void callRetrofit(){
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    CatApiService catApiService = retrofit.create(CatApiService.class);

    Call<List<Cat>> call = catApiService.getCats(API_KEY,3);
    call.enqueue(new Callback<List<Cat>>() {
        @Override
        public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
            if (response.isSuccessful()) {
                Log.d("ERRORES","NO HAY ERROR");
               cats = (ArrayList<Cat>) response.body();
                if (cats != null) {

                    for (Cat cat : cats) {
                        System.out.println(cat);
                    }
                }
            } else {
               Log.d("ERRORES","Request failed: " + response.code());
            }
            MyPagerAdapter myPagerAdapter = new MyPagerAdapter(MainActivity.this, cats.size(), (ArrayList<Cat>) cats);
            viewPager.setAdapter(myPagerAdapter);
        }

        @Override
        public void onFailure(Call<List<Cat>> call, Throwable t) {
            Log.d("ERRORES","Request failed: " + t.getMessage());
        }
    });
}
}


/**
 * https://developer.android.com/guide/navigation/navigation-swipe-view-2?hl=es-419
 https://developer.android.com/guide/navigation/navigation-swipe-view?hl=es-419#java */

/*ViewPager2 usa objetos FragmentStateAdapter como aprovisionamiento para que se muestren las páginas nuevas,
de manera que el FragmentStateAdapter usará la clase del fragmento que creaste anteriormente.*/
class MyPagerAdapter extends FragmentStateAdapter
{
    private  int NUM_ITEMS = 3;// NÚMERO DE FRAGMENTOS
    // TODO cambiar formato de antigua varialbe final
    private MainActivity fragmentActivity;
    private ArrayList<Cat> cats;
    public MyPagerAdapter(@NonNull FragmentActivity fragmentActivity,int num_items,ArrayList<Cat> cats) {
        super(fragmentActivity);
        this.fragmentActivity= (MainActivity) fragmentActivity;
        this.NUM_ITEMS = num_items;
        this.cats = cats;
    }

    public Fragment createFragment(int position) {
        FotoFragment fragment;
        Bundle args = new Bundle();
        Cat exampleCat = new Cat(
                "12 to 16 inches",
                "Southeast Asia",
                "https://api-ninjas.com/images/cats/abyssinian.jpg",
                3,  // familyFriendly
                3,  // shedding
                2,  // generalHealth
                5,  // playfulness
                5,  // childrenFriendly
                3,  // grooming
                5,  // intelligence
                5,  // otherPetsFriendly
                6,  // minWeight
                10, // maxWeight
                9,  // minLifeExpectancy
                15, // maxLifeExpectancy
                "Abyssinian"
        );

                fragment = FotoFragment.newInstance(cats.get(position)); //Este es el que cargará por defecto (el 0)

        fragment.setCallBackInterface(fragmentActivity);
        return fragment;

    }

    @Override
    public int getItemCount() {
        return NUM_ITEMS;
    }



}