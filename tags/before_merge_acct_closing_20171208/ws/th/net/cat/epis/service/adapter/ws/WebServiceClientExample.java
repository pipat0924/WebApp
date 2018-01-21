package th.net.cat.epis.service.adapter.ws;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;


public class WebServiceClientExample {
//	public static void main(String[] args) {
//		  GeoIPService geoIPService = new GeoIPService();
//		  GeoIPServiceSoap geoIPServiceSoap = geoIPService.getGeoIPServiceSoap();
//
//		  // Configuration Timeout Policy.
//		  Client client = ClientProxy.getClient(geoIPServiceSoap);
//		  HTTPConduit http = (HTTPConduit) client.getConduit();
//		   
//		  HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
//		   
//		  httpClientPolicy.setConnectionTimeout(36000);
//		  httpClientPolicy.setAllowChunking(false);
//		  httpClientPolicy.setReceiveTimeout(32000);
//		  http.setClient(httpClientPolicy);
//
//		  GeoIP geoIP = geoIPServiceSoap.getGeoIP("192.168.2.1");
//		  System.out.println("Returned: "+ geoIP.getReturnCode());
//		 }
	
	public static void main(String[] args) {
		  GeoIPService geoIPService = new GeoIPService();
		  GeoIPServiceSoap geoIPServiceSoap = geoIPService.getGeoIPServiceSoap();

		  // Configuration Timeout Policy.
		  Client client = ClientProxy.getClient(geoIPServiceSoap);
		  HTTPConduit http = (HTTPConduit) client.getConduit();
		   
		  HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
		   
		  httpClientPolicy.setConnectionTimeout(36000);
		  httpClientPolicy.setAllowChunking(false);
		  httpClientPolicy.setReceiveTimeout(32000);
		  http.setClient(httpClientPolicy);

		  GeoIP geoIP = geoIPServiceSoap.getGeoIP("192.168.2.1");
		  System.out.println("Returned: "+ geoIP.getReturnCode());
		 }
	
}
