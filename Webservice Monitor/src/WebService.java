
public class WebService {
	private String wsdl;
	private String name;
	private String request;
	private String expectedResponse;
	
	
	public String getWsdl() {
		return wsdl;
	}
	public void setWsdl(String wsdl) {
		this.wsdl = wsdl;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}

	public String getExpectedResponse() {
		return expectedResponse;
	}
	public void setExpectedResponse(String expectedResponse) {
		this.expectedResponse = expectedResponse;
	}
	

}
