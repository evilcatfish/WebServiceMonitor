import java.io.IOException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;


public class TestBean {

	private static String url = "http://FTS03CCAPP01TST.HGB.HS.INT:9080/homeserve/soap/ContractorLookup";

	public static void main(String[] args) {

		String requestData = "<?xml version=\"1.0\" encoding=\"utf-8\"?> "
				+ "<soap:Envelope xmlns:soap='http://schemas.xmlsoap.org/soap/envelope/'>"
				+ "<soap:Body>"
				+"<ns1:getContractors xmlns:ns1='http://www.homeserve.com/ClaimCenterIntegration/ContractorLookupWebService'>"
				+  "<ns1:selectionCriteria xmlns:xs='http://www.w3.org/2001/XMLSchema' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>"
				+   "<ns1:availableDate>17 Dec 2015 13:51</ns1:availableDate>"
				+ "<ns1:contractorType>NET</ns1:contractorType>"
				+ "<ns1:deployToNetwork>true</ns1:deployToNetwork>"
				+ "<ns1:maxReturn>50</ns1:maxReturn>"
				+ "<ns1:postcode>WS11 1TU</ns1:postcode>"
				+ "<ns1:solutionCode>W-P</ns1:solutionCode>"
				+ "<ns1:useVolumeSelection>true</ns1:useVolumeSelection>"
				+ "</ns1:selectionCriteria>"
				+ "</ns1:getContractors>"
				+ "</soap:Body>"
				+ "</soap:Envelope>";
		// Create an instance of HttpClient.
		HttpClient client = new HttpClient();

		// Create a method instance.
		// GetMethod method = new GetMethod(url);
		PostMethod method = new PostMethod(url);
		method.setRequestBody(requestData);
		method.setRequestHeader("SOAPAction", "");
		// Provide custom retry handler is necessary
		//                method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
		//                                            new DefaultHttpMethodRetryHandler(3, false));

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
	}
}
