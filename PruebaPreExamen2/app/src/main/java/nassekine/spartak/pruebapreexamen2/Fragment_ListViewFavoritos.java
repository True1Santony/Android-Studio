package nassekine.spartak.pruebapreexamen2;
import static nassekine.spartak.pruebapreexamen2.MainActivity.peliculas;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class Fragment_ListViewFavoritos extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Fragment_ListViewFavoritos() {

    }

    public static Fragment_ListViewFavoritos newInstance(String param1, String param2) {
        Fragment_ListViewFavoritos fragment = new Fragment_ListViewFavoritos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment__list_view_favoritos, container, false);

        ArrayList<Item> favoritos = new ArrayList<Item>();

        Context contexto=getContext();
        SharedPreferences preferencias = contexto.getSharedPreferences("pelis_favoritas", Context.MODE_PRIVATE);

        for(Item i : peliculas){
            //if(i.getFavorito()){
                if(preferencias.getBoolean(String.valueOf(i.getId()), false)){//false es por si
                    i.setFavorito(true);
                    favoritos.add(i);

                    Log.d("favoritos", i.getTitulo()+" recuperado");

                }
                Log.d("favoritos", i.getTitulo()+" no recuperado");
        }

        ListView listView = view.findViewById(R.id.lvFavoritos);

       Context context=getContext();

       ItemAdapter adaptador = new ItemAdapter(context, favoritos);
       listView.setAdapter(adaptador);


        return view;
    }
}