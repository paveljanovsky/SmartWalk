package smartwalk.creator;

import javax.vecmath.GMatrix;
import javax.vecmath.GVector;

import smartwalk.communication.JsonEngineDeleteTask;
import smartwalk.gpsUtils.GeographyUtils;
import android.location.Location;

public class SimulationCreator {
	
	private Location location;
	private final GMatrix worldRotation;
	private final GVector worldTranslation;

	public SimulationCreator(Location location){
		this.location = location;
		this.worldRotation = GeographyUtils.getRotationMatrix(location.getLongitude(), location.getLatitude());
		this.worldTranslation = GeographyUtils.getTranslationVector(location.getLongitude(), location.getLatitude(),location.getAltitude());
		clearDatabase();
	}

	private void clearDatabase() {
		new JsonEngineDeleteTask().execute();
	}

	public GMatrix getWorldRotation() {
		return worldRotation;
	}

	public GVector getWorldTranslation() {
		return worldTranslation;
	}

}
