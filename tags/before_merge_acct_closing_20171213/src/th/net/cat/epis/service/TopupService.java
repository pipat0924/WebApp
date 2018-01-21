package th.net.cat.epis.service;

import static java.util.Locale.*;
import static org.apache.commons.lang.StringUtils.*;
import static th.net.cat.epis.util.AppConstants.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@org.springframework.stereotype.Service
public class TopupService {

	private final SSLContext sslContext;
	private final SSLConnectionSocketFactory csf;
	private final HttpComponentsClientHttpRequestFactory requestFactory;
	private final RestTemplate restTemplate;
	@Value("${epis.topup.service.endpoint.username}")
	private String topupUserName;
	@Value("${epis.topup.service.endpoint.password}")
	private String topupPassword;
	@Value("${epis.topup.service.endpoint.service.key}")
	private String topupServiceKey;
	@Value("${epis.topup.service.endpoint.login}")
	private String topupServiceEndpointLogin;
	@Value("${epis.topup.service.endpoint.logout}")
	private String topupServiceEndpointLogout;
	@Value("${epis.topup.service.endpoint.findservice}")
	private String topupServiceEndpointFindService;
	@Value("${epis.topup.service.endpoint.findsubscriber}")
	private String topupServiceEndpointFindSubscriber;
	@Value("${epis.topup.service.endpoint.verifytopup}")
	private String topupServiceEndpointVerifyTopup;
	@Value("${epis.topup.service.endpoint.confirmtopup}")
	private String topupServiceEndpointConfirmTopup;
	@Value("${epis.topup.service.endpoint.verifycancel}")
	private String topupServiceEndpointVerifyCancel;
	@Value("${epis.topup.service.endpoint.confirmcancel}")
	private String topupServiceEndpointConfirmCancel;

	public TopupService() throws Exception {
		sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
		csf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		});
		requestFactory = new HttpComponentsClientHttpRequestFactory(HttpClients.custom().setSSLSocketFactory(csf).build());
		restTemplate = new RestTemplate(requestFactory);
	}
	private HttpHeaders getHttpHeaders() {
		return getHttpHeaders(null);
	}
	private HttpHeaders getHttpHeaders(String authTokens) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setPragma("no-cache");
		httpHeaders.setCacheControl("no-cache");
		httpHeaders.setConnection("keep-alive");
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		httpHeaders.setAccept(Arrays.asList(MediaType.ALL));
		httpHeaders.set("Accept-Encoding", "gzip, deflate, sdch");
		httpHeaders.set("Accept-Language", "en-US,en;q=0.8,th;q=0.6");	
		httpHeaders.set("service_key", topupServiceKey);
		httpHeaders.setAccessControlAllowHeaders(Arrays.asList("accept", "content-type"));
		httpHeaders.setAccessControlAllowMethods(Arrays.asList(HttpMethod.GET, HttpMethod.POST));
		if (authTokens != null) httpHeaders.set("auth_token", authTokens);
		return httpHeaders;
	}

	public JSONObject login() throws JSONException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", topupUserName);
		params.put("password", topupPassword);
		ResponseEntity<String> responseEntity = restTemplate.exchange(topupServiceEndpointLogin, HttpMethod.POST, new HttpEntity<Map<String, String>>(params, getHttpHeaders()), String.class);
		JSONObject jsonObject = new JSONObject(isBlank(responseEntity.getBody()) ? "{}" : responseEntity.getBody());
		jsonObject.accumulate("service_key", topupServiceKey);
		jsonObject.accumulate(TOPUP_FIELD_STATUS_CODE, responseEntity.getStatusCode().toString());
		return jsonObject;
	}

	public JSONObject findServices(String authTokens) throws JSONException {
		ResponseEntity<String> responseEntity = restTemplate.exchange(topupServiceEndpointFindService, HttpMethod.GET, new HttpEntity<Map<String, String>>(null, getHttpHeaders(authTokens)), String.class);
		JSONObject jsonObject = new JSONObject(isBlank(responseEntity.getBody()) ? "{}" : responseEntity.getBody());
		jsonObject.accumulate(TOPUP_FIELD_STATUS_CODE, responseEntity.getStatusCode().toString());
		return jsonObject;
	}

	public JSONObject findSubscribers(String authTokens, String serviceName, String serviceNo) throws JSONException {
		ResponseEntity<String> responseEntity = restTemplate.exchange(topupServiceEndpointFindSubscriber +"/"+ serviceName +"/"+ serviceNo, HttpMethod.GET, new HttpEntity<Map<String, String>>(null, getHttpHeaders(authTokens)), String.class);
		JSONObject jsonObject = new JSONObject(isBlank(responseEntity.getBody()) ? "{}" : responseEntity.getBody());
		jsonObject.accumulate(TOPUP_FIELD_STATUS_CODE, responseEntity.getStatusCode().toString());
		
		return jsonObject;
	}

	public JSONObject verifyTopup(String authTokens, String serviceName, String serviceNo, String subscriberName, String transid, String reftransid, String amount) throws JSONException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("transid", transid);
		params.put("amount", amount);
		params.put("effdate", FastDateFormat.getInstance("yyyyMMdd", ENGLISH).format(System.currentTimeMillis()));
		params.put("reftransid", reftransid);
		params.put("subscriberno", subscriberName);
		ResponseEntity<String> responseEntity = restTemplate.exchange(topupServiceEndpointVerifyTopup +"/"+ serviceName +"/"+ serviceNo, HttpMethod.POST, new HttpEntity<Map<String, String>>(params, getHttpHeaders(authTokens)), String.class);
		JSONObject jsonObject = new JSONObject(isBlank(responseEntity.getBody()) ? "{}" : responseEntity.getBody());
		jsonObject.accumulate("service_key", topupServiceKey);
		jsonObject.accumulate(TOPUP_FIELD_STATUS_CODE, responseEntity.getStatusCode().toString());
		return jsonObject;
	}

	public JSONObject confirmTopup(String authTokens, String serviceName, String serviceNo, String subscriberName, String transid, String reftransid, String amount) throws JSONException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("transid", transid);
		params.put("amount", amount);
		params.put("effdate", FastDateFormat.getInstance("yyyyMMdd", ENGLISH).format(System.currentTimeMillis()));
		params.put("reftransid", reftransid);
		params.put("subscriberno", subscriberName);
		ResponseEntity<String> responseEntity = restTemplate.exchange(topupServiceEndpointConfirmTopup +"/"+ serviceName +"/"+ serviceNo, HttpMethod.POST, new HttpEntity<Map<String, String>>(params, getHttpHeaders(authTokens)), String.class);
		JSONObject jsonObject = new JSONObject(isBlank(responseEntity.getBody()) ? "{}" : responseEntity.getBody());
		jsonObject.accumulate("service_key", topupServiceKey);
		jsonObject.accumulate(TOPUP_FIELD_STATUS_CODE, responseEntity.getStatusCode().toString());
		return jsonObject;
	}

	public JSONObject verifyCancel(String authTokens, String serviceName, String serviceNo, String subscriberName, String transid, String reftransid, String amount) throws JSONException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("transid", transid);
		params.put("amount", amount);
		params.put("effdate", FastDateFormat.getInstance("yyyyMMdd", ENGLISH).format(System.currentTimeMillis()));
		params.put("reftransid", reftransid);
		params.put("subscriberno", subscriberName);
		ResponseEntity<String> responseEntity = restTemplate.exchange(topupServiceEndpointVerifyCancel +"/"+ serviceName +"/"+ serviceNo, HttpMethod.POST, new HttpEntity<Map<String, String>>(params, getHttpHeaders(authTokens)), String.class);
		JSONObject jsonObject = new JSONObject(isBlank(responseEntity.getBody()) ? "{}" : responseEntity.getBody());
		jsonObject.accumulate("service_key", topupServiceKey);
		jsonObject.accumulate(TOPUP_FIELD_STATUS_CODE, responseEntity.getStatusCode().toString());
		return jsonObject;
	}

	public JSONObject confirmCancel(String authTokens, String serviceName, String serviceNo, String subscriberName, String transid, String reftransid, String amount) throws JSONException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("transid", transid);
		params.put("amount", amount);
		params.put("effdate", FastDateFormat.getInstance("yyyyMMdd", ENGLISH).format(System.currentTimeMillis()));
		params.put("reftransid", reftransid);
		params.put("subscriberno", subscriberName);
		ResponseEntity<String> responseEntity = restTemplate.exchange(topupServiceEndpointConfirmCancel +"/"+ serviceName +"/"+ serviceNo, HttpMethod.POST, new HttpEntity<Map<String, String>>(params, getHttpHeaders(authTokens)), String.class);
		JSONObject jsonObject = new JSONObject(isBlank(responseEntity.getBody()) ? "{}" : responseEntity.getBody());
		jsonObject.accumulate("service_key", topupServiceKey);
		jsonObject.accumulate(TOPUP_FIELD_STATUS_CODE, responseEntity.getStatusCode().toString());
		return jsonObject;
	}
}