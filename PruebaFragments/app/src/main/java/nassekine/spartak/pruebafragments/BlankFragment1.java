package nassekine.spartak.pruebafragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;


public class BlankFragment1 extends Fragment {


    private WebView webView;
    private EditText editTextUrl;
    private Button btnIr;

    public BlankFragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank1, container, false);

        // Inicializar vistas
        webView = view.findViewById(R.id.webview);
        editTextUrl = view.findViewById(R.id.editTextText);
        btnIr = view.findViewById(R.id.btnIr);

        // Configurar clic del botón
        btnIr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editTextUrl.getText().toString();
                cargarUrl(url);
            }
        });

        return view;
    }

    private void cargarUrl(String url) {
        // Configurar la carga de URL en el WebView
        webView.loadUrl(url);
    }
}