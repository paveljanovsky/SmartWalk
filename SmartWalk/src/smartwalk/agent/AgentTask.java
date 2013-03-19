package smartwalk.agent;

import smartwalk.communication.JsonEnginePostTask;
import smartwalk.communication.TelemetryMessage;
import android.os.AsyncTask;

public class AgentTask extends AsyncTask<AgentTaskData, AgentTaskData, AgentTaskData> {

	@Override
	protected AgentTaskData doInBackground(AgentTaskData... dataArray) {
		AgentTaskData data = dataArray[0];
		if(data != null){
			
			return data;
		}
		return null;
	}
	
	 protected void onProgressUpdate(AgentTaskData... progress) {
         
     }

     protected void onPostExecute(AgentTaskData data) {
    	 //send my location data to the server
    	 new JsonEnginePostTask().execute(new TelemetryMessage(data));
    	 
    	 //show my location data
    	 if(data.getMainActivity() != null && data.getLocationCart() != null){
    		 data.getMainActivity().showLocation(data.getLocationCart());
    		 data.getMainActivity().showLocationGPS(data.getLocationGPS());
    	 }
     }
    
}


