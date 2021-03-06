import java.net.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import org.apache.xml.serialize.*;
import java.io.*;

public class WeatherWebserviceTester {

	public static void main(String[] args) {
		WeatherWebserviceTester weatherWebserviceTester =
				new WeatherWebserviceTester();
		try {
			weatherWebserviceTester.getWeather("Washington");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getWeather(String city) throws MalformedURLException,
	IOException {

		//Code to make a webservice HTTP request
		String responseString = "";
		String outputString = "";
		String wsURL = "http://www.deeptraining.com/webservices/weather.asmx";
		URL url = new URL(wsURL);
		URLConnection connection = url.openConnection();
		HttpURLConnection httpConn = (HttpURLConnection)connection;
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		String xmlInput =
				" <soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://litwinconsulting.com/webservices/\">\n" +
						" <soapenv:Header/>\n" +
						" <soapenv:Body>\n" +
						" <web:GetWeather>\n" +
						" <!--Optional:-->\n" +
						" <web:City>" + city + "</web:City>\n" +
						" </web:GetWeather>\n" +
						" </soapenv:Body>\n" +
						" </soapenv:Envelope>";

		byte[] buffer = new byte[xmlInput.length()];
		buffer = xmlInput.getBytes();
		bout.write(buffer);
		byte[] b = bout.toByteArray();
		String SOAPAction =
				"http://litwinconsulting.com/webservices/GetWeather";
		// Set the appropriate HTTP parameters.
		httpConn.setRequestProperty("Content-Length",
				String.valueOf(b.length));
		httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		httpConn.setRequestProperty("SOAPAction", SOAPAction);
		httpConn.setRequestMethod("POST");
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		OutputStream out = httpConn.getOutputStream();
		//Write the content of the request to the outputstream of the HTTP Connection.
		out.write(b);
		out.close();
		//Ready with sending the request.

		//Read the response.
		InputStreamReader isr =
				new InputStreamReader(httpConn.getInputStream());
		BufferedReader in = new BufferedReader(isr);

		//Write the SOAP message response to a String.
		while ((responseString = in.readLine()) != null) {
			outputString = outputString + responseString;
		}
		//Parse the String output to a org.w3c.dom.Document and be able to reach every node with the org.w3c.dom API.
		Document document = parseXmlFile(outputString);
		NodeList nodeLst = document.getElementsByTagName("GetWeatherResult");
		String weatherResult = nodeLst.item(0).getTextContent();
		System.out.println("Weather: " + weatherResult);

		//Write the SOAP message formatted to the console.
		String formattedSOAPResponse = formatXML(outputString);
		System.out.println(formattedSOAPResponse);
		return weatherResult;
	}



	//format the XML in your String
	public String formatXML(String unformattedXml) {
		try {
			Document document = parseXmlFile(unformattedXml);
			OutputFormat format = new OutputFormat(document);
			format.setIndenting(true);
			format.setIndent(3);
			format.setOmitXMLDeclaration(true);
			Writer out = new StringWriter();
			XMLSerializer serializer = new XMLSerializer(out, format);
			serializer.serialize(document);
			return out.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Document parseXmlFile(String in) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(in));
			return db.parse(is);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

//https://technology.amis.nl/2011/06/29/how-to-call-a-call-a-webservice-directly-from-java-without-webservice-library/
