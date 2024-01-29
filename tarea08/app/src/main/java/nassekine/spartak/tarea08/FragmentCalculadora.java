package nassekine.spartak.tarea08;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class FragmentCalculadora extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private RadioButton sumar, restar, multiplicar, dividir;
    private TextView resultado;
    private EditText oper1, oper2;
    private Button operar;


    public FragmentCalculadora() {

    }


    public static FragmentCalculadora newInstance(String param1, String param2) {
        FragmentCalculadora fragment = new FragmentCalculadora();
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

        View view= inflater.inflate(R.layout.fragment_calculadora, container, false);

        sumar=view.findViewById(R.id.rbtnSumar);
        restar=view.findViewById(R.id.rbtnRestar);
        multiplicar=view.findViewById(R.id.rbtnMultiplicar);
        dividir=view.findViewById(R.id.rbtnDividir);

        resultado=view.findViewById(R.id.txtResultado);

        oper1=view.findViewById(R.id.edtxt1);
        oper2=view.findViewById(R.id.editTextText2);

        operar=view.findViewById(R.id.btnOperar);

        operar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double primero,segundo;

                if(sumar.isChecked()){

                    primero=Double.parseDouble(oper1.getText().toString());
                    segundo=Double.parseDouble(oper2.getText().toString());

                    resultado.setText(String.valueOf(primero+segundo));

                } else if (restar.isChecked()) {

                    primero=Double.parseDouble(oper1.getText().toString());
                    segundo=Double.parseDouble(oper2.getText().toString());

                    resultado.setText(String.valueOf(primero-segundo));

                } else if (multiplicar.isChecked()) {

                    primero=Double.parseDouble(oper1.getText().toString());
                    segundo=Double.parseDouble(oper2.getText().toString());

                    resultado.setText(String.valueOf(primero*segundo));

                } else if (dividir.isChecked()) {

                    primero=Double.parseDouble(oper1.getText().toString());
                    segundo=Double.parseDouble(oper2.getText().toString());

                    resultado.setText(String.valueOf(primero/segundo));

                }else{
                    Toast.makeText(getContext(),"Seleccione una opción",Toast.LENGTH_LONG).show();
                }

                oper1.setText("");
                oper2.setText("");
                ocultaTeclado();
            }
        });



        return view;
    }

    private void ocultaTeclado() {

        View view = getView();

        InputMethodManager inputMethodManager = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);


    }

}