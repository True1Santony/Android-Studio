package nassekine.spartak.pruebafragments;






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



public class BlankFragment2 extends Fragment {

    private RadioButton sumar, restar, multiplicar, dividir;
    private TextView resultado;
    private EditText oper1, oper2;
    private Button operar;


    public BlankFragment2() {


    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_blank2, container, false);

        sumar=view.findViewById(R.id.rbtnSumar);
        restar=view.findViewById(R.id.rbtnRestar);
        multiplicar=view.findViewById(R.id.rbtnMultiplicar);
        dividir=view.findViewById(R.id.rbtnDividir);

        resultado=view.findViewById(R.id.txtResultado);

        oper1=view.findViewById(R.id.edtxt1);
        oper2=view.findViewById(R.id.edtxt2);

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