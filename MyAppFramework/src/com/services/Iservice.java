package com.services;

import java.util.Properties;

import com.interfaces.model.Iproxy;



public interface Iservice {

	
	public Iproxy handle(EventCodes event,Properties payload);
	
	
}
