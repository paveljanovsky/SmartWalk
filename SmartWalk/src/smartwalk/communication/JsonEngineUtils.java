package smartwalk.communication;

import java.util.StringTokenizer;

import javax.vecmath.Vector3d;

public class JsonEngineUtils {

	public static final String APP_ID = "smartwalk-janovsky"; 
	public static final String JSONENGINE_URL = "http://" + APP_ID + ".appspot.com/_je";
	public static final String DOC_TYPE = "msg"; 
	public static final String URL_POSTFIX_GET = "?sort=_createdAt.desc&limit=100";	
	public static final String URL_POSTFIX_DELETE = "?_method=delete";
	
	public static final String TAG = "JsonEngine";

	public static Vector3d parseStringToVector(String string) {
		StringTokenizer st = new StringTokenizer(string.substring(1, string.length()-1), ",");
		double x = Double.parseDouble(st.nextToken());
		double y = Double.parseDouble(st.nextToken());
		double z = Double.parseDouble(st.nextToken());
		return new Vector3d(x,y,z);
	}
}
