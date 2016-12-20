<<<<<<< HEAD

public class WebService {
	String wsdl;
	String name;
	String request;
	String expectedResponse;
	
	
	
	

}
=======
import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

public class WebService {

	String name;
	String url;
	String requestData;
	String[] checks;
	String result;

	public WebService(String n, String u, String r) {
		name = n;
		url = u;
		requestData = r;
		
	}

	public String testMe() {
		// Create an instance of HttpClient.
		HttpClient client = new HttpClient();

		// Create a method instance.
		PostMethod method = new PostMethod(url);
		method.setRequestBody(requestData);
		method.setRequestHeader("SOAPAction", "");

		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = method.getResponseBody();

			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary data
			System.out.println("Response is >>> "+ new String(responseBody));
			result = new String(responseBody);
						

		} catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		
		return result;  
		
	}


}
>>>>>>> origin/master
