package smartwalk.agent;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import smartwalk.communication.JsonEngineGetTask;
import smartwalk.communication.JsonEnginePostTask;
import smartwalk.communication.JsonEngineUtils;
import smartwalk.communication.TelemetryMessage;
import android.os.AsyncTask;

public class AgentTask extends AsyncTask<AgentTaskData, AgentTaskData, AgentTaskData> {

	
	ArrayList<TelemetryMessage> telemetryMessages;
	AgentTaskData data;
	
	@Override
	protected AgentTaskData doInBackground(AgentTaskData... dataArray) {
		data = dataArray[0];
//		new JsonEngineGetTask().execute(JsonEngineUtils.JSONENGINE_URL + "/" + JsonEngineUtils.DOC_TYPE + JsonEngineUtils.URL_POSTFIX_GET);
		publishProgress();
		if(telemetryMessages != null){
			System.out.println(telemetryMessages.size());
		}
		if(data != null){
			
			return data;
		}
		return null;
	}
	
	 protected void onProgressUpdate(AgentTaskData... progress) {
		 try {
				telemetryMessages = new JsonEngineGetTask().execute(JsonEngineUtils.JSONENGINE_URL + "/" + JsonEngineUtils.DOC_TYPE + JsonEngineUtils.URL_POSTFIX_GET).get();
				data.getMainActivity().showDebug(telemetryMessages);
				data.getAgent().addTelemetryMessages(telemetryMessages);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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


