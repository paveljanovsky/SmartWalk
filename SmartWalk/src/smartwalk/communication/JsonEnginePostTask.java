package smartwalk.communication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class JsonEnginePostTask extends AsyncTask<TelemetryMessage, Integer, Long> {
	private int statusCode;
	//private ProgressDialog progressDialog;
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();/*
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
	
	protected Long doInBackground(TelemetryMessage... msgs) {
		// Create a new HttpClient and Post Header  
		HttpClient httpclient = new DefaultHttpClient();  
		HttpPost httppost = new HttpPost(JsonEngineUtils.JSONENGINE_URL + "/" + JsonEngineUtils.DOC_TYPE);

		try {  
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("agentName",msgs[0].getAgentName()));
			nameValuePairs.add(new BasicNameValuePair("locationCart",msgs[0].getLocationCart().toString()));
			//nameValuePairs.add(new BasicNameValuePair("locationGPS",msgs[0].getLocationGPS().toString()));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));

			HttpResponse response = httpclient.execute(httppost);
			statusCode = response.getStatusLine().getStatusCode();
			Log.v(JsonEngineUtils.TAG, "status code = " + statusCode);
		} catch (ClientProtocolException e) {  
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Long.valueOf(0);
	}

	protected void onProgressUpdate(Integer... progress) {
		//TODO show progress
	}

	protected void onPostExecute(Long result) {/*
		if ((statusCode & 200) != 0) {
			Toast.makeText(context, "Posted. Updating view...", Toast.LENGTH_SHORT).show();
			new JsonEngineGetTask().execute(JsonEngineConstants.JSONENGINE_URL + "/" + JsonEngineConstants.DOC_TYPE + JsonEngineConstants.URL_POSTFIX_GET);
		} else {
			Toast.makeText(context, "Post Error (" + statusCode + ")", Toast.LENGTH_SHORT).show();
		}
		progressDialog.dismiss();*/
	}
}
