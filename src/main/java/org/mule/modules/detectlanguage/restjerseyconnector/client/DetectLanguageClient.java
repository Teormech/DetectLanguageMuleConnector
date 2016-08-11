package org.mule.modules.detectlanguage.restjerseyconnector.client;

import java.util.List;

import org.mule.modules.detectlanguage.DetectLanguageConnector;
import org.mule.modules.detectlanguage.restjerseyconnector.entities.Language;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

/**
 * Client to communicate with Detect language API
 * @author apersidsky
 *
 */
public class DetectLanguageClient {

	private Client client;
	private WebResource apiResource;
	private DetectLanguageConnector connector;

	public DetectLanguageClient(DetectLanguageConnector connector) {
		this.connector = connector;

		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		this.client = Client.create(clientConfig);
		this.apiResource = this.client.resource(getConnector().getApiURL() + "/" + getConnector().getApiVersion());
	}

	/**
	 * Retrieve all available languages
	 * @return List of Language objects
	 */
	public List<Language> getLanguages() {
		List<Language> languages = getApiResource().path("/languages").get(new GenericType<List<Language>>(){});
		
		return languages;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public WebResource getApiResource() {
		return apiResource;
	}

	public void setApiResource(WebResource apiResource) {
		this.apiResource = apiResource;
	}

	public DetectLanguageConnector getConnector() {
		return connector;
	}

	public void setConnector(DetectLanguageConnector connector) {
		this.connector = connector;
	}

}
