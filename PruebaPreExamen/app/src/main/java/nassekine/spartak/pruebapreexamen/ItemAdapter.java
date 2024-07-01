package nassekine.spartak.pruebapreexamen;

import android.app.DownloadManager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;

import nassekine.spartak.pruebapreexamen.R;

public class ItemAdapter extends BaseAdapter {

    //private static final String API_KEY = "cdce22f8e7ebd0a03760e2c0b1584862";
    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/upcoming?api_key=cdce22f8e7ebd0a03760e2c0b1584862";
    private static final String IMAGE_URL = "https://image.tmdb.org/t/p/w500";
    private Context context;
    private List<Item> items;
    public List<Item> favoritos;

    public ItemAdapter(Context context, List<Item> favoritos) {
        this.context = context;
        this.favoritos = favoritos;
    }

    public ItemAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
        this.favoritos = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(context);
        //Toast.makeText( context,"Datos cargados 1 ", Toast.LENGTH_SHORT).show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, BASE_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                JSONArray jsonArray = jsonObject.optJSONArray("results");
               // Log.d("JSON_RESPONSE", jsonArray.toString());
                //Toast.makeText( context,"Datos cargados 2 ", Toast.LENGTH_SHORT).show();
            for(int i = 0; i < jsonArray.length(); i++){
                try {
                    JSONObject peliculaObject =jsonArray.getJSONObject(i);

                    String title = peliculaObject.getString("title");
                 //   Log.d("JSON_RESPONSE", title);

                    String descripcion = peliculaObject.getString("overview");
                 //   Log.d("JSON_RESPONSE", descripcion);

                    String rutaImagen = peliculaObject.getString("backdrop_path");
                    Log.d("JSON_RESPONSE", rutaImagen);

                    String estreno = peliculaObject.getString("release_date");
                    //Log.d("JSON_RESPONSE", estreno);




                    items.add(new Item(rutaImagen,title,descripcion,estreno));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
                Toast.makeText( context,"Datos cargados 3 ", Toast.LENGTH_SHORT).show();


                notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("ERROR_VOLLEY", "Error en la solicitud: " + volleyError.getMessage());
            }
        });

        queue.add(request);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        }

        //Toast.makeText( context,"AAAAAAAAA ", Toast.LENGTH_SHORT).show();
        Item itemActual = items.get(position);
        TextView tvTitulo = convertView.findViewById(R.id.tvTitulo);
        TextView tvDescripcion = convertView.findViewById(R.id.tvDescripcion);
        TextView tvEstreno = convertView.findViewById(R.id.tvFechaEstreno);
        ImageView ivImagen = convertView.findViewById(R.id.ivCaratula);

        tvTitulo.setText(itemActual.getTitulo());
        tvDescripcion.setText(itemActual.getDescripcion());
        tvEstreno.setText(itemActual.getEstreno());

        Picasso.get().load(IMAGE_URL + itemActual.getRutaImagen()).into(ivImagen);

        //Log.d("JSON_RESPONSE", itemActual.getTitulo());


        ImageButton imageButton = convertView.findViewById(R.id.imageButton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!itemActual.isFavorito()){

                    itemActual.setFavorito(true);
                    imageButton.setImageResource(android.R.drawable.btn_star_big_on);
                    favoritos.add(itemActual);

                }else{
                    itemActual.setFavorito(false);
                    imageButton.setImageResource(android.R.drawable.btn_star_big_off);
                    favoritos.remove(itemActual);
                }
            }
        });

        return convertView;
    }
}
