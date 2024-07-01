package com.example.gatos_retrofit_java.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.gatos_retrofit_java.R;
import com.example.gatos_retrofit_java.clases.Cat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView tvMarca, tvDescripcion;

    private ImageView imgvLogo, imgvFoto;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private Cat mParam2;

    public DetalleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleFragment newInstance(String param1, Cat param2) {
        DetalleFragment fragment = new DetalleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putParcelable(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getParcelable(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getParcelable(ARG_PARAM2);
        }
        int selector = Integer.parseInt(mParam1);
        tvMarca=view.findViewById(R.id.tvMarca);
        tvDescripcion=view.findViewById(R.id.tvDescripcion);
        imgvLogo=view.findViewById(R.id.imgvLogo);
        imgvFoto=view.findViewById(R.id.imgvFoto);


                // bmv
                Glide.with(this)
                             .load(mParam2.getImage_link())
                             .apply(RequestOptions.centerCropTransform())
                        .into(imgvLogo);



      tvMarca.setText(mParam2.getName());
     tvDescripcion.setText(mParam2.getOrigin());


        return view;
    }
}