package nassekine.spartak.pruebapreexamen2;


import static nassekine.spartak.pruebapreexamen2.MainActivity.peliculas;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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


public class Fragment_ListViewPrincipal extends Fragment {


    private static final String IMAGE_URL = "https://image.tmdb.org/t/p/w500";

    ItemAdapter adapter;
    private Context context;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public Fragment_ListViewPrincipal() {}

    public static Fragment_ListViewPrincipal newInstance(String param1, String param2) {
        Fragment_ListViewPrincipal fragment = new Fragment_ListViewPrincipal();
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

        View view= inflater.inflate(R.layout.fragment__list_view_principal, container, false);

        ListView listView = view.findViewById(R.id.lvPrincipal);

        context=getContext();

        adapter= new ItemAdapter(context,peliculas);
        listView.setAdapter(adapter);

        return view;

    }


}