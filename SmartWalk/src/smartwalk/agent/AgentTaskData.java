package smartwalk.agent;

import javax.vecmath.Vector3d;

import smartwalk.main.MainActivity;
import android.location.Location;

public class AgentTaskData {

	private Vector3d locationCart;
	private Location locationGPS;
	private MainActivity mainActivity;

	public AgentTaskData(Vector3d locationCart, Location locationGPS,
			MainActivity mainActivity) {
		this.locationCart = locationCart;
		this.locationGPS = locationGPS;
		this.mainActivity = mainActivity;
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
