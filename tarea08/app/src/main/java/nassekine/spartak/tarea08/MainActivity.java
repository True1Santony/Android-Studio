package nassekine.spartak.tarea08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //preparamos el FragmentTransacion y establecemos uno como inicial
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        FragmentNavegador fragmentNavegador = new FragmentNavegador();

        //agrego el fragmento navegador en el contenedor y se confirma
        transaction.add(R.id.fragmentContainerView,fragmentNavegador);
        transaction.commit();

    }

    public void seleccionarFragment(View v){

        Fragment miFragment;

        if(v==findViewById(R.id.btnCalculadora)){

            miFragment = new FragmentCalculadora();

        } else if (v==findViewById(R.id.btnNavegador)) {

            miFragment = new FragmentNavegador();

        }else{

            miFragment = new FragmentNavegador();

        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //se reemplaza el fragmen en funcion del boton pulsado
        transaction.replace(R.id.fragmentContainerView, miFragment);
        transaction.addToBackStack(null);//se guarda el fragment anterior para poder regresar
        transaction.commit();

    }

}