package smartwalk.communication;

import javax.vecmath.Vector3d;

import smartwalk.agent.AgentTaskData;

import android.location.Location;

public class TelemetryMessage {

	private Vector3d locationCart;
	private Location locationGPS;
	private String agentName;
	private String docId;
	private Long timeStamp;

	public TelemetryMessage(String agentName, Vector3d locationCart,
			Location locationGPS) {
		this.locationCart = locationCart;
		this.locationGPS = locationGPS;
		this.agentName = agentName;
	}

	public TelemetryMessage(AgentTaskData agentTaskData) {
		this.locationCart = agentTaskData.getLocationCart();
		this.locationGPS = agentTaskData.getLocationGPS();
		this.agentName = agentTaskData.getAgentName();
	}

	public Vector3d getLocationCart() {
		return locationCart;
	}

	public Location getLocationGPS() {
		return locationGPS;
	}

	public String getAgentName() {
		return agentName;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(agentName).append(",").append(locationCart).append(",")
				.append(locationGPS);
		return sb.toString();
	}

	public void setDocId(String string) {
		this.docId = string;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

}
