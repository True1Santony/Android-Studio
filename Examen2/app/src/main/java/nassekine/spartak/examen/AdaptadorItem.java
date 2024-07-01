package nassekine.spartak.examen;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaptadorItem extends BaseAdapter {

  //  private static Participante actualGlobal;
    private Context context;
    private List<Participante> items;


    public AdaptadorItem (Context contexto,List<Participante> items ){
        this.context= contexto;
        this.items= items;


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

        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        }

        Participante pActual = items.get(position);

        TextView tvNombreParticipante = convertView.findViewById(R.id.tvParticipante);
        TextView tvPrecioParticipante = convertView.findViewById(R.id.tvImporte);
        ImageView ivImagen =convertView.findViewById(R.id.ivImagenParticipante);

        tvNombreParticipante.setText(pActual.getNombre());
        tvPrecioParticipante.setText(String.valueOf(pActual.getGastosPagados()));

        Log.d("ItemAdapter", "datos:   " + pActual.getNombre() + " - " + pActual.getGastosPagados()+ pActual.getImagen());

        Picasso.get().load(pActual.getImagen()).into(ivImagen);

     /* ivImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                actualGlobal= pActual;

                //seleccionaFragment();

            }
        });*/




        return convertView;
    }

    /*public void seleccionaFragment(View view){

        FragmentEditar miFragment = new FragmentEditar() ;



        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContenedor, miFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }*/



    /*
    *   private ArrayList<Item> cargaArrayItems() {

        List<Participante> items;

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, https://raw.githubusercontent.com/nataliainformatica/2024/main/emojis.json, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                JSONArray jsonArray = jsonObject.optJSONArray();

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

        return participante;

    }
    *
    * */

}
