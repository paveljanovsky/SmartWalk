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
import android.widget.Toast;

public class JsonEngineDeleteTask extends AsyncTask<String, Integer, Long> {
	private int statusCode;
	private ProgressDialog progressDialog;

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
	
	protected Long doInBackground(String... ids) {
		// Create a new HttpClient and Post Header  
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost;
		if(ids.length != 0){
			httppost = new HttpPost(JsonEngineUtils.JSONENGINE_URL + "/" + JsonEngineUtils.DOC_TYPE + "/" + ids[0] + JsonEngineUtils.URL_POSTFIX_DELETE);
		}
		else{
			httppost = new HttpPost(JsonEngineUtils.JSONENGINE_URL + "/" + JsonEngineUtils.DOC_TYPE + JsonEngineUtils.URL_POSTFIX_DELETE);
		}

		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			if(ids.length != 0){
				nameValuePairs.add(new BasicNameValuePair("msg", ids[0]));
			}
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));

			HttpResponse response = httpclient.execute(httppost);
			statusCode = response.getStatusLine().getStatusCode();
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
			Toast.makeText(context, "Deleted. Updating View...", Toast.LENGTH_SHORT).show();
			new JsonEngineGetTask().execute(JSONENGINE_URL + "/" + DOC_TYPE + URL_POSTFIX_GET);
		} else {
			Toast.makeText(context, "Delete Error (" + statusCode + ")", Toast.LENGTH_SHORT).show();
		}
		progressDialog.dismiss();*/
	}
}
