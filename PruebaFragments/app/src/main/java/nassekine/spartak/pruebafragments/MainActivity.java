package nassekine.spartak.pruebafragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        BlankFragment1 fragmentInicial = new BlankFragment1();

        transaction.add(R.id.fragContainerV,fragmentInicial);
        transaction.commit();


    }


    public void seleccionarFragment(View v){

        Fragment miFragment;

        if(v==findViewById(R.id.btnFrag1)){//si se pulsa el boton 1

            miFragment=new BlankFragment1();

        } else if (v==findViewById(R.id.btnFrag2)) {

            miFragment=new BlankFragment2();

        } else if (v==findViewById(R.id.btnFrag3)) {

            miFragment=new BlankFragment3();

        }else {

            miFragment=new BlankFragment1();

        }

        //facemos las transiciones entre los fragment

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragContainerV,miFragment);
        transaction.addToBackStack(null);//para guardar el grafment anterior en caso de dar a back
        transaction.commit();

    }
}