package com.example.gatos_retrofit_java.fragmentos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.gatos_retrofit_java.R;
import com.example.gatos_retrofit_java.clases.CallBackInterface;
import com.example.gatos_retrofit_java.clases.Cat;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FotoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FotoFragment extends Fragment {
    private CallBackInterface callBackInterface;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";


    // TODO: Rename and change types of parameters
    private Cat mParam1;


    public FotoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.

     * @return A new instance of fragment BultacoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FotoFragment newInstance(Cat param1) {
        FotoFragment fragment = new FotoFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getParcelable(ARG_PARAM1);

        }
    }

    public void setCallBackInterface(CallBackInterface callBackInterface){
        this.callBackInterface=callBackInterface;
    }
    public void tonto(){

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("FotoFragment", "onResume");
        if(callBackInterface!=null){
            callBackInterface.callBackMethod(mParam1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foto, container, false);
        // Inflate the layout for this fragment
        ImageButton imageButton =view.findViewById(R.id.imageView);
        if(getArguments()!= null){
            mParam1 = getArguments().getParcelable(ARG_PARAM1);
            Picasso.get().load(mParam1.getImage_link() ).error(R.mipmap.ic_launcher).resize(400, 400).centerInside().into(imageButton);
        }

        return view;
    }


    // Decodifica la imagen con las dimensiones deseadas

    private Bitmap redimensiona(int tu_imagen){

        // Obtén las dimensiones deseadas para reducir la imagen
        int anchoDeseado = 150; // Puedes ajustar según tus necesidades
        int altoDeseado = 150;  // Puedes ajustar según tus necesidades
        BitmapFactory.Options opciones = new BitmapFactory.Options();
        opciones.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), tu_imagen, opciones);

// Calcula la escala de reducción
        opciones.inSampleSize = calcularFactorDeEscalado(opciones, anchoDeseado, altoDeseado);

// Decodifica la imagen con la escala calculada
        opciones.inJustDecodeBounds = false;
        Bitmap bitmapRedimensionado = BitmapFactory.decodeResource(getResources(), tu_imagen, opciones);
    return bitmapRedimensionado;
    // Asigna el bitmap redimensionado al ImageView

    }
    private int calcularFactorDeEscalado(BitmapFactory.Options opciones, int anchoDeseado, int altoDeseado) {
        final int anchoOriginal = opciones.outWidth;
        final int altoOriginal = opciones.outHeight;

        int factorEscalado = 1;

        if (anchoOriginal > anchoDeseado || altoOriginal > altoDeseado) {
            final int mitadAncho = anchoOriginal / 2;
            final int mitadAlto = altoOriginal / 2;

            while ((mitadAncho / factorEscalado) >= anchoDeseado && (mitadAlto / factorEscalado) >= altoDeseado) {
                factorEscalado *= 2;
            }
        }

        return factorEscalado;
    }

}