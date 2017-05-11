package com.nkang.kxmoment.baseobject;

import java.util.List;

public class ClientInformation {
	private String ClientID;
	private String ClientIdentifier;
	private String ClientDescription;
	private List<String> ConsumedWebService;
	public String getClientID() {
		return ClientID;
	}
	public void setClientID(String clientID) {
		ClientID = clientID;
	}
	public String getClientIdentifier() {
		return ClientIdentifier;
	}
	public void setClientIdentifier(String clientIdentifier) {
		ClientIdentifier = clientIdentifier;
	}
	public String getClientDescription() {
		return ClientDescription;
	}
	public void setClientDescription(String clientDescription) {
		ClientDescription = clientDescription;
	}
	public List<String> getConsumedWebService() {
		return ConsumedWebService;
	}
	public void setConsumedWebService(List<String> consumedWebService) {
		ConsumedWebService = consumedWebService;
	}
	
	
}
