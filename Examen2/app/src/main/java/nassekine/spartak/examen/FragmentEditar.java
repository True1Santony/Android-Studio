package nassekine.spartak.examen;


import static nassekine.spartak.examen.MainActivity.participantes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import nassekine.spartak.examen.dataBase.DbHelper;


public class FragmentEditar extends Fragment {


    private static final String URL="https://raw.githubusercontent.com/nataliainformatica/2024/main/emojis.json";
    private Participante participanteEditar;
    private ImageView imagenParticipante;


    public FragmentEditar() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        View view = inflater.inflate(R.layout.fragment_editar, container, false);

        if(getArguments() != null){
            participanteEditar = (Participante) getArguments().getSerializable("participante");
        }

        Button guardar = view.findViewById(R.id.btnGuardar);
        Button aleatorio = view.findViewById(R.id.btnSortImage);

        EditText nombre = view.findViewById(R.id.etvNombre);
        EditText importePagado = view.findViewById(R.id.etvCantidad);
        imagenParticipante = view.findViewById(R.id.ivParticipante);

        nombre.setText(participanteEditar.getNombre());
        importePagado.setText(String.valueOf(participanteEditar.getGastosPagados()));

        Picasso.get().load(participanteEditar.getImagen()).into(imagenParticipante);


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //aqui actalizar en el array de participantes los campos modificados que puede ser imagen, nombre y cantidad
                participanteEditar.setGastosPagados(Integer.parseInt(importePagado.getText().toString()));
                participanteEditar.setNombre(nombre.getText().toString());
                //participanteEditar.setImagen(imagenParticipante.); esto se actualiza solo si se pulsa en aleatorio, si no no se actualizaa.


                guardaParticipante(participanteEditar);
                DbHelper dbHelper = new DbHelper(getContext());
                dbHelper.actualizarParticipante(participanteEditar);

                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.popBackStack();
            }
        });

        aleatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cargaImagenAleatoria();

            }
        });


        return view;
    }

    public void cargaImagenAleatoria() {

        ArrayList<String> emojis = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        //jeson array porque solo hay un array sin etiqueta

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                for (int i = 0; i < response.length(); i++) {
                    JSONObject emoji = response.optJSONObject(i);
                    String url = emoji.optString("image");
                    emojis.add(url);
                    Log.d("url", url);
                }

                int aleatorio = (int) (Math.random() * emojis.size());
                participanteEditar.setImagen(emojis.get(aleatorio));//guarda la imagen el el objeto a editar
                Picasso.get().load(participanteEditar.getImagen()).into(imagenParticipante);


            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Log.d("error", "se ha producido un erro"+ volleyError.toString());
            }
        });

        requestQueue.add(jsonObjectRequest);



    }

    public void guardaParticipante(Participante participanteAguardar){//guarda el participante el el lugar en funcion del id del mismo

        for (int i = 0; i < participantes.size(); i++)

        {
            if (participantes.get(i).getId()==participanteAguardar.getId()) {
                participantes.set(i, participanteAguardar);

            }
        }

    }
}