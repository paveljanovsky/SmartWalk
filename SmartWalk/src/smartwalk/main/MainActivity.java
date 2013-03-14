package smartwalk.main;

import java.util.Vector;

import javax.vecmath.Vector3d;

import smartwalk.agent.Agent;
import smartwalk.creator.SimulationCreator;
import smartwalk.sensor.TelemetrySensor;
import janovsky.smartwalk.R;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText editTextLongitude;
	private EditText editTextLatitude;
	private EditText editTextXCoordinate;
	private EditText editTextYCoordinate;
	private TelemetrySensor telemetrySensor;
	private Agent agent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editTextLongitude = (EditText) findViewById(R.id.editTextLogitude);
		editTextLatitude = (EditText) findViewById(R.id.EditTextLatitude);
		editTextXCoordinate = (EditText) findViewById(R.id.EditTextXCoordinate);
		editTextYCoordinate = (EditText) findViewById(R.id.EditTextYCoordinate);

		final Button bCreateSim = (Button) findViewById(R.id.bCreateSim);
		bCreateSim.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (telemetrySensor.isSensing()) {
					SimulationCreator simulationCreator = new SimulationCreator(
							telemetrySensor.getLocationGPS());
					telemetrySensor.setWorldRotation(simulationCreator
							.getWorldRotation());
					telemetrySensor.setWorldTranslation(simulationCreator
							.getWorldTranslation());
					
					agent.run();
					
				}
				else{
					Toast errorMessage = Toast.makeText(getApplicationContext(), "Please wait for the gps module to initialize", Toast.LENGTH_SHORT);
					errorMessage.show();
				}
			}
		});

		createSensors();
		agent = new Agent(telemetrySensor, this);
		
	}

	

	private void createSensors() {
		this.telemetrySensor = new TelemetrySensor(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}



	public void showLocation(Vector3d location) {
		editTextXCoordinate.setText(""+location.x);
		editTextYCoordinate.setText(""+location.y);
	}

}
