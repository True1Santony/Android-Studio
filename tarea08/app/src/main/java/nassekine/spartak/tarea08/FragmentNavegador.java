package nassekine.spartak.tarea08;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;


public class FragmentNavegador extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;


    private WebView webView;
    private EditText editTextUrl;
    private Button btnIr;
    public FragmentNavegador() {

    }


    public static FragmentNavegador newInstance(String param1, String param2) {
        FragmentNavegador fragment = new FragmentNavegador();
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
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_navegador, container, false);

        webView = view.findViewById(R.id.webview);
        editTextUrl = view.findViewById(R.id.editTextText);
        btnIr = view.findViewById(R.id.btnIr);

        btnIr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = editTextUrl.getText().toString();
                    if(url.startsWith("https://www.")){//comprobando si la url es correcta
                        webView.loadUrl(url);
                    }else{

                        String urlNormalizada="https://www."+url;
                        webView.loadUrl(urlNormalizada);
                    }

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