package com.controller;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import com.interfaces.model.Iproxy;
import com.services.EventCodes;
import com.services.Iservice;
import com.services.ServiceRequest;
import com.services.ServiceRequestQueue;

public class UIcontroller {

	private Iservice service;
	/** Current service response */
	private ConcurrentHashMap<String, Iproxy> serviceresponse=new ConcurrentHashMap<>();
	private static UIcontroller instance = new UIcontroller();

	/** This is a singleton class */
	private UIcontroller() {

		
	}

	public static UIcontroller getInstance() {

		return instance;
	}

	/** This method will be called by screens to sent ui event */
	public boolean sendEvent(Properties payload) {

		return start(payload);
	}

	private boolean start(Properties ev) {

		// get Event code from event factory
		EventCodes event = UIEventFactory.getEvent(ev.getProperty("ecode"));
		if (event == EventCodes.INVALID) {

			return false;
		}

		return process(event, ev);
		

	}

	private boolean process(EventCodes event, Properties payload) {

		ServiceRequest request = new ServiceRequest(event, payload);
		boolean isadded = ServiceRequestQueue.getInstance().addRequest(request);
        return isadded;
	}

	public ConcurrentHashMap<String, Iproxy> getServiceResponseQue() {

		return this.serviceresponse;
	}

	/** service layer will use this method to notify ui thread */
	public synchronized void notifyServiceResponse(EventCodes ev) {

		synchronized (serviceresponse) {
			
			serviceresponse.notify();
		}
		

	}

	/**
	 * Every screen should call this method whenever it wants to wait for data
	 * processing in background
	 */
	public void waitforEvent(String c, UieventListener screen) {

		try {
			synchronized (serviceresponse) {
				
				serviceresponse.wait();
			}
			
			screen.RequestCompleted(serviceresponse.get(c));

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



}
