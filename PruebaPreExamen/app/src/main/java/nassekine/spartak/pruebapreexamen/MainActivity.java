package nassekine.spartak.pruebapreexamen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.nio.file.attribute.AclEntryType;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "cdce22f8e7ebd0a03760e2c0b1584862";
    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/upcoming?api_key=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Context context = this;

        Button btnPrincipal = findViewById(R.id.btnPrincipal);
        Button btnFavoritos = findViewById(R.id.btnFavoritos);

        ListView listView = findViewById(R.id.listView);
        ItemAdapter itemAdapter = new ItemAdapter(context);
        listView.setAdapter(itemAdapter);



        btnPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}