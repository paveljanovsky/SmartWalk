package smartwalk.main;

import java.util.ArrayList;
import java.util.Vector;

import javax.vecmath.Vector3d;

import smartwalk.agent.Agent;
import smartwalk.communication.TelemetryMessage;
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
	private Agent agent;
	private EditText editTextDebug;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editTextLongitude = (EditText) findViewById(R.id.editTextLogitude);
		editTextLatitude = (EditText) findViewById(R.id.EditTextLatitude);
		editTextXCoordinate = (EditText) findViewById(R.id.EditTextXCoordinate);
		editTextYCoordinate = (EditText) findViewById(R.id.EditTextYCoordinate);
		editTextDebug = (EditText) findViewById(R.id.editTextDebug);
		
		//TODO implement agent code name
		agent = new Agent("agent1",this);

		final Button bCreateSim = (Button) findViewById(R.id.bCreateSim);
		bCreateSim.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (agent.getTelemetrySensor().isSensing()) {
					SimulationCreator simulationCreator = new SimulationCreator(
							agent.getTelemetrySensor().getLocationGPS());
					agent.getTelemetrySensor().setWorldRotation(simulationCreator
							.getWorldRotation());
					agent.getTelemetrySensor().setWorldTranslation(simulationCreator
							.getWorldTranslation());

					
					bCreateSim.setEnabled(false);
				}
				else{
					Toast errorMessage = Toast.makeText(getApplicationContext(), "Please wait for the gps module to initialize", Toast.LENGTH_SHORT);
					errorMessage.show();
				}
			}
		});

		
		
	}

	

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}



	public void showLocation(Vector3d location) {
		String xLoc = ""+location.x;
		String yLoc = ""+location.y;
		editTextXCoordinate.setText(xLoc);
		editTextYCoordinate.setText(yLoc);
	}





	public void showLocationGPS(Location locationGPS) {
		editTextLatitude.setText(""+locationGPS.getLatitude());
		editTextLongitude.setText(""+locationGPS.getLongitude());
	}





	public void showDebug(ArrayList<TelemetryMessage> telemetryMessages) {
		editTextDebug.setText("tel count: "+telemetryMessages.size());
	}

}
