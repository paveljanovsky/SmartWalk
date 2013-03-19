package smartwalk.sensor;

import java.util.Vector;

import javax.vecmath.GMatrix;
import javax.vecmath.GVector;
import javax.vecmath.Vector3d;

import smartwalk.agent.Agent;
import smartwalk.agent.AgentTask;
import smartwalk.agent.AgentTaskData;
import smartwalk.gpsUtils.GeographyUtils;
import smartwalk.main.MainActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class TelemetrySensor {
	
	private Location locationGPS;
	private Vector3d locationCart;
	private GMatrix worldRotation;
	private GVector worldTranslation;
	private Agent agent;
	private MainActivity mainActivity;

	public Location getLocationGPS() {
		return locationGPS;
	}

	public void setWorldRotation(GMatrix worldRotation) {
		this.worldRotation = worldRotation;
	}

	public void setWorldTranslation(GVector worldTranslation) {
		this.worldTranslation = worldTranslation;
	}

	public GMatrix getWorldRotation() {
		return worldRotation;
	}

	public GVector getWorldTranslation() {
		return worldTranslation;
	}

	public TelemetrySensor(final Agent agent, MainActivity activity) {
		this.mainActivity = activity;
		this.agent = agent;
		LocationManager locationManager = (LocationManager) mainActivity
				.getSystemService(Context.LOCATION_SERVICE);
		LocationListener locationListener = new LocationListener() {

			

			

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onLocationChanged(Location location) {
				locationGPS = location;
				if(worldTranslation != null && worldRotation != null){
					locationCart = GeographyUtils.fromGPStoCart(location.getLongitude(), location.getLatitude(), location.getAltitude(), worldRotation, worldTranslation);
					AgentTaskData data = new AgentTaskData(agent.getName(), locationCart, locationGPS, mainActivity);
					new AgentTask().execute(data);
				}
				// Vector position =
				// gpsUtilsController.getCartPositionFromGPS(location);
				// editTextLongitude.setText(""+location.getLongitude());
				// editTextLatitude.setText(""+location.getLatitude());
				// int x = (int) ((100/360.0) * (180 +
				// location.getLongitude()));
				// int y = (int) ((100/180.0) * (90 - location.getLatitude()));
				// editTextXCoordinate.setText(""+x);
				// editTextYCoordinate.setText(""+y);
			}
		};
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000,
				0, locationListener);
	}
	
	public Vector3d senseLocation(){
		return locationCart;
	}

	/**
	 * 
	 * @return true if the gps module is providing location data
	 */
	public boolean isSensing() {
		if(locationGPS != null){
			return true;
		}
		else{
			return false;
		}
	}
	
}


