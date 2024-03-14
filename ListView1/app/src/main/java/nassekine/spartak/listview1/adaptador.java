package nassekine.spartak.listview1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class adaptador extends BaseAdapter {

    private static LayoutInflater inflater=null;

    Context context;
    String[][]datos;

    public adaptador(Context context, String[][] datos, int[] datosImg) {
        this.context = context;
        this.datos = datos;
        this.datosImg = datosImg;

        inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    int[] datosImg;//total de elementos
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.elemento_lista,null);

        TextView titulo = view.findViewById(R.id.tvTitulo);
        TextView director = view.findViewById(R.id.tvDirector);
        TextView duracion = view.findViewById(R.id.tvDuracion);
        ImageView imagen = view.findViewById(R.id.ivImagen);
        RatingBar calificacion = view.findViewById(R.id.rating);

        ImageButton imageButton= view.findViewById(R.id.imageButton);

        titulo.setText(datos[i][0]);
        director.setText(datos[i][1]);
        duracion.setText("Duración: "+datos[i][2]);
        imagen.setImageResource(datosImg[i]);

        calificacion.setProgress(Integer.valueOf(datos[i][3]));

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(imageButton.isSelected()){

                    imageButton.setImageResource(android.R.drawable.btn_star_big_on);

                }else {

                    imageButton.setImageResource(android.R.drawable.btn_star_big_off);

                }

                imageButton.setSelected(!imageButton.isSelected());
            }
        });

        //imagen.setTag(i);

        /*imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent visorImagen = new Intent(context,)

            }
        });*/

        return view;
    }

    @Override
    public int getCount() {//total de elementos
        return datosImg.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


}
