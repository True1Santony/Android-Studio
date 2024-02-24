package nassekine.spartak.pruedassensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mLight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonTodosServicios = findViewById(R.id.btn1);
        Button botonMuestraLux = findViewById(R.id.btn2);

        //sensorManager
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //obtengo el sensor de luminosidad ambiental por el sensorManager
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        //boton para mostrar todos los sensores
        botonTodosServicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List< Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

                for(Sensor sensor: deviceSensors){

                    Toast.makeText(v.getContext()," "+sensor.getName()+" "+sensor.getMaxDelay(),Toast.LENGTH_LONG).show();
                }
            }
        });

        //boton Lux registra al pulsar el boton, somienza la escucha de los eventos disparados por el sensor(onSensorChanged)
        botonMuestraLux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSensorManager.registerListener(MainActivity.this,mLight,SensorManager.SENSOR_DELAY_NORMAL);


            }
        });


    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float lumines = event.values[0];
        Toast.makeText(this,"Paso por cambio de lumines, valor:  "+lumines,Toast.LENGTH_SHORT).show();

        //termina la escucha del sensor para no leer constantemente, solo al pulsar el boton de lux.
         //mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    protected void onResume() {
        super.onResume();


        Toast.makeText(this,"paso por el onResume ",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected  void onPause(){
        super.onPause();

        //se asegura que se deja de escuchar al pausar el main.
        mSensorManager.unregisterListener(this);
        Toast.makeText(this,"paso por el onPause ",Toast.LENGTH_SHORT).show();
    }

}