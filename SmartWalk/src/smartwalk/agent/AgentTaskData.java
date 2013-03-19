package smartwalk.agent;

import javax.vecmath.Vector3d;

import smartwalk.main.MainActivity;
import android.location.Location;

public class AgentTaskData {

	private Vector3d locationCart;
	private Location locationGPS;
	private MainActivity mainActivity;
	private String agentName;

	public AgentTaskData(String agentName, Vector3d locationCart, Location locationGPS,
			MainActivity mainActivity) {
		this.agentName = agentName;
		this.locationCart = locationCart;
		this.locationGPS = locationGPS;
		this.mainActivity = mainActivity;
	}

	public String getAgentName() {
		return agentName;
	}

	public Vector3d getLocationCart() {
		return locationCart;
	}

	public Location getLocationGPS() {
		return locationGPS;
	}

	public MainActivity getMainActivity() {
		return mainActivity;
	}

}
