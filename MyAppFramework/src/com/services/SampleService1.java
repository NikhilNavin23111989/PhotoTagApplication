package com.services;

import java.util.Properties;

import com.interfaces.model.Iproxy;
import com.proxy.impl.PhotoProxy;

public class SampleService1 implements Iservice {

	@Override
	public Iproxy handle(EventCodes event, Properties payload) {

		Iproxy result = null;

		switch (event) {

		case VIEW:
			result = handleview(payload);
			break;
		case ADD:
			result = handleAdd(payload);
			break;

		case DELETE:
			break;

		case SHARE:
			break;
			
		case SEARCH:
			result=handlesearch(payload);
             break;
        
		case TAG:
			result=handleTag(payload);
			
		}

		return result;
	}

	private Iproxy handleTag(Properties payload) {
		String type = payload.getProperty("TYPE");

		return SubserviceFactory.getSubService(type).handleTag(payload);
	}

	private Iproxy handleview(Properties payload) {
		String type = payload.getProperty("TYPE");

		return SubserviceFactory.getSubService(type).handleView(payload);
	}

	private Iproxy handleAdd(Properties payload) {
		String type = payload.getProperty("TYPE");

		return SubserviceFactory.getSubService(type).handleAdd(payload);
	}
	
	private Iproxy handlesearch(Properties payload){

		String type = payload.getProperty("TYPE");

		return SubserviceFactory.getSubService(type).handleSearch(payload);
	
		
	}
}
