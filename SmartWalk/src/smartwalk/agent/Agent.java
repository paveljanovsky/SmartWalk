package smartwalk.agent;

import javax.vecmath.Vector3d;

import smartwalk.main.MainActivity;
import smartwalk.sensor.TelemetrySensor;

public class Agent {

	private static final long TIME_STEP = 1000;
	private TelemetrySensor telemetrySensor;
	private MainActivity mainActivity;
	private Vector3d location;
//	private SerialDelayedCallQueue callQueue;

	public Agent(MainActivity mainActivity) {
	
	this.mainActivity = mainActivity;
	createSensors();
//	this.callQueue = new SerialDelayedCallQueue();
	}

	

	private void createSensors() {
		this.telemetrySensor = new TelemetrySensor(this, mainActivity);
	}

	public void run() {
//		callQueue.addRepeatedCall(TIME_STEP,
//				(TimeUnit) TimeUnit.MILLISECONDS,
//				new Runnable() {
//
//					@Override
//					public void run() {
//						location = telemetrySensor.senseLocation();
//						if(location != null){
//							mainActivity.showLocation(location);
//						}
//					}
//		});
	}
	
	public TelemetrySensor getTelemetrySensor() {
		return telemetrySensor;
	}

}
