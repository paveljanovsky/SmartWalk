package smartwalk.communication;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.vecmath.Vector3d;

import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class JsonEngineGetTask extends AsyncTask<String, Integer, ArrayList<TelemetryMessage>> {
//	private TelemetryMessage[] items;
	private ArrayList<TelemetryMessage> telemetryMessages;
	private ProgressDialog progressDialog;
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
//		progressDialog = new ProgressDialog(context);
//		progressDialog.setMessage("通信中...");
//		progressDialog.setOnCancelListener(new OnCancelListener() {
//			@Override
//			public void onCancel(DialogInterface dialog) {
//				cancel(false);
//			}
//		});
//		progressDialog.show();
	}

	protected ArrayList<TelemetryMessage> doInBackground(String... urls) {
		try {
			InputStream is = new URL(urls[0]).openConnection().getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			ByteArrayBuffer buf = new ByteArrayBuffer(0);

			int current = 0;
			while((current = bis.read()) != -1){
				buf.append((byte)current);
			}
			Log.v(JsonEngineUtils.TAG, "capacity = " + buf.capacity());

			/* Convert the Bytes read to a String. */
			String html = new String(buf.toByteArray());
			JSONArray jsons = new JSONArray(html);

			telemetryMessages = new ArrayList<TelemetryMessage>(jsons.length());
			for (int i = 0; i < jsons.length(); i++) {
			    JSONObject jsonObj = jsons.getJSONObject(i);
			    Vector3d locationCart = JsonEngineUtils.parseStringToVector(jsonObj.getString("locationCart"));
			    TelemetryMessage tm = new TelemetryMessage(jsonObj.getString("agentName"),locationCart,null);
			    tm.setDocId(jsonObj.getString("_docId"));
			    tm.setTimeStamp(Long.parseLong(jsonObj.getString("_updatedAt")));
			    telemetryMessages.add(tm);
			}
			return telemetryMessages;
		} catch (Exception e) {
			Log.e(JsonEngineUtils.TAG, e.getStackTrace().toString());
			return null;
		}

	}


	protected void onProgressUpdate(Integer... progress) {
		//TODO show progress
	}

	protected void onPostExecute(ArrayList<TelemetryMessage> result) {
//		progressDialog.dismiss();
	}
}
