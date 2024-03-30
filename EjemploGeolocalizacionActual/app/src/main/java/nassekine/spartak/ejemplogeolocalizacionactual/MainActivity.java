package nassekine.spartak.ejemplogeolocalizacionactual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.PrimitiveIterator;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback, LocationListener {

    private TextView tvLatitud,tvLongitud, tvAltura, tvPrecision;
    private LocationManager locationManager;

    public Criteria criteria;
    public String bestProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAltura = findViewById(R.id.tvAltura);
        tvLatitud = findViewById(R.id.tvLatitud);
        tvLongitud = findViewById(R.id.tcLongitud);
        tvPrecision = findViewById(R.id.tvPrecision);

        getLocalizacion();

    }

    private void getLocalizacion() {

            //obtiene el servicio de sistema correspondiente al servicio de ubicación
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            //para especificar los criterios de selección para la obtención de proveedores de ubicación
        criteria = new Criteria();

        //devuelve el proveedor posicionamiento segun criteria
        bestProvider = String.valueOf(locationManager.getBestProvider(criteria,true)).toString();

        //solicitud de permisos al usuario
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},10);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Location location= locationManager.getLastKnownLocation(bestProvider);
            if(location !=null){

                Toast.makeText(MainActivity.this, "Determinacion de ubicación por última conocida", Toast.LENGTH_LONG).show();
                tvLatitud.setText(" Latitud: " + String.valueOf(location.getLatitude())+"º"); //se mide en grados
                tvLongitud.setText(" Longitud: " + String.valueOf(location.getLongitude())+"º"); //se mide en grados
                tvAltura.setText(" Altitud: " + String.valueOf(location.getAltitude())+"m"); //se mide en metros
                tvPrecision.setText(" Precisión: "+ String.valueOf(location.getAccuracy())+"m"); //se mide en metros (radio)

            }else{

                locationManager.requestLocationUpdates(bestProvider, 1000, 0, this);

            }

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }

    @Override
    public void onFlushComplete(int requestCode) {
        LocationListener.super.onFlushComplete(requestCode);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}