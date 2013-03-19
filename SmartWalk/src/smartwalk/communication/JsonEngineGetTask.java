package smartwalk.communication;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
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

public class JsonEngineGetTask extends AsyncTask<String, Integer, Long> {
	private TelemetryMessage[] items;
	private ProgressDialog progressDialog;
	
	@Override
	protected void onPreExecute() {/*
		super.onPreExecute();
		progressDialog = new ProgressDialog(context);
		progressDialog.setMessage("通信中...");
		progressDialog.setOnCancelListener(new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				cancel(false);
			}
		});
		progressDialog.show();*/
	}

	protected Long doInBackground(String... urls) {
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

			items = new TelemetryMessage[jsons.length()];
			for (int i = 0; i < jsons.length(); i++) {
			    JSONObject jsonObj = jsons.getJSONObject(i);
			    Vector3d locationCart = JsonEngineUtils.parseStringToVector(jsonObj.getString("locationCart"));
			    
			    items[i] = new TelemetryMessage(jsonObj.getString("agentName"),locationCart,null);
			    items[i].setDocId(jsonObj.getString("_docId"));
			}
		} catch (Exception e) {
			Log.e(JsonEngineUtils.TAG, e.getStackTrace().toString());
		}
		return Long.valueOf(0);
	}

	protected void onProgressUpdate(Integer... progress) {
		//TODO show progress
	}

	protected void onPostExecute(Long result) {
		if (items != null) {
//			adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, msgs);
			/*adapter = new CustomArrayAdapter(context, items);
			listView.setAdapter(adapter);
			Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();*/
		}
		progressDialog.dismiss();
	}
}
