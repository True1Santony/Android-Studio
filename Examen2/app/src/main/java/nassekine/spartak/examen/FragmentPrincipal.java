package nassekine.spartak.examen;

import static nassekine.spartak.examen.MainActivity.participantes;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import nassekine.spartak.examen.dataBase.DbHelper;


public class FragmentPrincipal extends Fragment {

    private Context context;//conexto del fragment
    private AdaptadorItem adapter;//adaptador de la lista

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //se infla el diseño del fragmento
        View view = inflater.inflate(R.layout.fragment_principal,container, false);

        //se obtiene el listview
        ListView listView = view.findViewById(R.id.lvPrincipal);
        //se obtiene el contexto
        context = getContext();

        //se crea el adaptador
        adapter = new AdaptadorItem(context,participantes);
        listView.setAdapter(adapter);

        //se registra el menu contextual para la lista
        registerForContextMenu(listView);

        return view;
    }

    public FragmentPrincipal() { }



    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}


    //metodo para crear el menu contextual
    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        //se infla el menu contextual desde el diseño
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menuflo, menu);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

       // ContextMenu.ContextMenuInfo info = item.getMenuInfo();
        //obtengo la posicion del item seleccionado
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int posicion = info.position;//posicion del item seleccionado dentro de la lista con el menu

        Participante participanteSeleccionado = participantes.get(posicion);

            if(item.getItemId()== R.id.editar){

                //creo una instancia del fragmendestino y le paso por bundel el objeto a editar
                FragmentEditar fragmentEditar = new FragmentEditar();
                Bundle bundel = new Bundle();
                bundel.putSerializable("participante", participanteSeleccionado);
                fragmentEditar.setArguments(bundel);


                //getParentFragmentManager es equivalente a getFragmentManager si se llama desde un fragment
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainerView, fragmentEditar);
                transaction.addToBackStack(null);
                transaction.commit();

                return true;

            } else if (item.getItemId()== R.id.borrar) {

                DbHelper dbHelper = new DbHelper(getContext());
                dbHelper.borrarParticipante(participantes.get(posicion));//borra de la base de datos primero y luego del array
                participantes.remove(posicion);//borra del array que se usa en la app

                adapter.notifyDataSetChanged();

                return true;
            }else{
                return super.onOptionsItemSelected(item);
            }
    }


}