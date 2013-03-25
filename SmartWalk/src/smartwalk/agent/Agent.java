package smartwalk.agent;

import java.util.ArrayList;
import java.util.HashMap;

import javax.vecmath.Vector3d;

import smartwalk.communication.TelemetryMessage;
import smartwalk.main.MainActivity;
import smartwalk.sensor.TelemetrySensor;

public class Agent {

	private static final long TIME_STEP = 1000;
	private TelemetrySensor telemetrySensor;
	private MainActivity mainActivity;
	private Vector3d location;
	private String name;
	
	private HashMap<String, TelemetryMessage> radarUpdates = new HashMap<String, TelemetryMessage>();

	public Agent(String name, MainActivity mainActivity) {
	this.name = name;
	this.mainActivity = mainActivity;
	createSensors();
	}

	

	private void createSensors() {
		this.telemetrySensor = new TelemetrySensor(this, mainActivity);
	}
	
	public TelemetrySensor getTelemetrySensor() {
		return telemetrySensor;
	}



	public String getName() {
		return this.name;
	}



	public void createAgentTask(AgentTaskData data) {
		new AgentTask().execute(data);
	}



	public void addTelemetryMessages(
			//TODO add only the newest record for each agent
			ArrayList<TelemetryMessage> telemetryMessages) {
		for(TelemetryMessage m : telemetryMessages){
			this.radarUpdates.put(m.getAgentName(), m);
		}
		
	}

}
