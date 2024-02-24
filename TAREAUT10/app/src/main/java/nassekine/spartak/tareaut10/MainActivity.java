package nassekine.spartak.tareaut10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensorLuminosidad;
    private TextView textMuestraLux;
    private boolean primeraEjecucion=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //administrador de sensores
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //obtengo el sensor de luminosidad ambiental por el sensorManager
        sensorLuminosidad = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        textMuestraLux = findViewById(R.id.txtLuminosidad);

    }
    /*cambio de estado de valores del sensor.
     *
     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        double lumines = sensorEvent.values[0];

        textMuestraLux.setText("Luminosidad: "+lumines+"lx");

    }

    //precision ha cambiado
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {


        if (primeraEjecucion) {
            // Este bloque de código se ejecuta solo en la primera llamada, no implica cambio de precision
            primeraEjecucion = false;

        } else {
            // ejecuta las llamadas posteriores, tras la inicial
            Toast.makeText(this,"Precición baja",Toast.LENGTH_SHORT).show();
        }


    }

    /*cuando se reactiva
     *Registra el sensor de luminosidad para comenzar la escucha de sus eventos
     */
    @Override
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(this,sensorLuminosidad,SensorManager.SENSOR_DELAY_NORMAL);
        Toast.makeText(this,"Paso por onResume, Sensor registrado",Toast.LENGTH_SHORT).show();


    }

    /*cuando se pausa
     *deja de registrar los eventos del sensor.
     */
    @Override
    protected void onPause(){
        super.onPause();

        sensorManager.unregisterListener(this);
        Toast.makeText(this,"Paso por onPause, se deja de registrar",Toast.LENGTH_SHORT).show();

    }

    /*@Override
    protected void onDestroy() {
        super.onDestroy();

        sensorManager.unregisterListener(this);
        Toast.makeText(this,"Paso por onDestroy, se deja de registrar",Toast.LENGTH_SHORT).show();
    }*/
}