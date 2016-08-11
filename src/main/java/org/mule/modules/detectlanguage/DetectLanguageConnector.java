package org.mule.modules.detectlanguage;

import java.util.List;

import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;

import org.mule.api.annotations.lifecycle.Start;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;
import org.mule.modules.detectlanguage.restjerseyconnector.client.DetectLanguageClient;
import org.mule.modules.detectlanguage.restjerseyconnector.entities.Language;

@Connector(name="detect-language", friendlyName="DetectLanguage Connector")
public class DetectLanguageConnector {

    @Configurable
    @Optional
    @Default("http://ws.detectlanguage.com")
    private String apiURL;

    @Configurable
    @Optional
    @Default("0.2")
    private double apiVersion;

    private DetectLanguageClient client;
    
    @Start
    public void init() {
        setClient(new DetectLanguageClient(this));
    }
    
	@Processor
	public List<Language> getLanguages()  {
		return getClient().getLanguages();
	}
	
	public String getApiURL() {
		return apiURL;
	}

	public void setApiURL(String apiURL) {
		this.apiURL = apiURL;
	}

	public double getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(double apiVersion) {
		this.apiVersion = apiVersion;
	}

	public DetectLanguageClient getClient() {
		return client;
	}

	public void setClient(DetectLanguageClient client) {
		this.client = client;
	}
}