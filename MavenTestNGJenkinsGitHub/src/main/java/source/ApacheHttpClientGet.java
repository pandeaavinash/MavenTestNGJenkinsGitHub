package source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.generated.vos.content.Restaurant;
import com.generated.vos.content.Restaurant_;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ApacheHttpClientGet 
{
	static DefaultHttpClient httpClient = null;
	
	@SuppressWarnings("deprecation")
	public static void get()
	{
		try{
		httpClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(
			"https://fudu.getsimplifide.com/fudu_api/v1/restaurants/1");
		getRequest.addHeader("accept", "application/json");
		getRequest.addHeader("api_key", "12325328745875");
		getRequest.addHeader("api_secret", "9786875764764");

		HttpResponse response = httpClient.execute(getRequest);

		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode());
		}

		BufferedReader br = new BufferedReader(
                         new InputStreamReader((response.getEntity().getContent())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
			
			Gson gson = new Gson();
			JsonParser jp = new JsonParser();
			JsonElement je =  jp.parse(output);
			je = je.getAsJsonObject().get("restaurant");
			JsonObject obj =  je.getAsJsonObject();
			output = obj.toString();
			System.out.println(je);
			
			Restaurant_ rest = gson.fromJson(output, Restaurant_.class);
			System.out.println(rest.getAddress());
		}

		httpClient.getConnectionManager().shutdown();

	  } catch (ClientProtocolException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();
	  }

	}
	
}
