package smartwalk.agent;

import javax.vecmath.Vector3d;

import smartwalk.main.MainActivity;
import smartwalk.sensor.TelemetrySensor;

public class Agent {

	private TelemetrySensor telemetrySensor;
	private MainActivity mainActivity;
	private boolean isRunning = true;
	private Vector3d location;

	public Agent(TelemetrySensor telemetrySensor, MainActivity mainActivity) {
	this.telemetrySensor = telemetrySensor;
	this.mainActivity = mainActivity;
	}

	public void run() {
		while(isRunning){
			location = telemetrySensor.senseLocation();
			if(location != null){
				mainActivity.showLocation(location);
			}
			
//			isRunning = false;
		}
	}

}
