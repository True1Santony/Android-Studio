package nassekine.spartak.pruebapreexamen2;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends BaseAdapter {



    private Context context;
    private List<Item> items;

    public ItemAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;

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

        Item itemActual = items.get(position);

        TextView tvTitulo = convertView.findViewById(R.id.tvTitulo);
        TextView tvDescripcion = convertView.findViewById(R.id.tvDescripcion);
        TextView tvEstreno = convertView.findViewById(R.id.tvFechaEstreno);
        ImageView ivImagen = convertView.findViewById(R.id.ivCaratula);
        ImageButton imageButton = convertView.findViewById(R.id.imageButton);

        tvTitulo.setText(itemActual.getTitulo());
        tvDescripcion.setText(itemActual.getDescripcion());
        tvEstreno.setText(itemActual.getEstreno());

        if(itemActual.getFavorito()){

            imageButton.setImageResource(android.R.drawable.btn_star_big_on);
        }else{
            imageButton.setImageResource(android.R.drawable.btn_star_big_off);
        }

        Picasso.get().load(itemActual.getRutaImagen()).into(ivImagen);

        //ImageButton imageButton = convertView.findViewById(R.id.imageButton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = context.getSharedPreferences("pelis_favoritas", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if(!itemActual.getFavorito()){

                    itemActual.setFavorito(true);
                    imageButton.setImageResource(android.R.drawable.btn_star_big_on);
                    editor.putBoolean(String.valueOf(itemActual.getId()), true);
                    editor.apply();
                    Log.d("ItemAdapter", "Guardando favorito: " + itemActual.getId() + " - " + itemActual.getFavorito());

                }else{
                    itemActual.setFavorito(false);
                    imageButton.setImageResource(android.R.drawable.btn_star_big_off);
                    editor.putBoolean(String.valueOf(itemActual.getId()), false);
                    editor.apply();
                }


            }
        });

        return convertView;
    }
}
