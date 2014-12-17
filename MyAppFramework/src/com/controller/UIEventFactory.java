package com.controller;

import java.util.HashMap;
import java.util.HashSet;

import com.services.EventCodes;

public class UIEventFactory {
	
	
	private static  HashMap<String, EventCodes> uieventlist = new HashMap<String, EventCodes>();
	
	static{
		
		uieventlist.put("VIEW",EventCodes.VIEW);
		uieventlist.put("ADD",EventCodes.ADD);
		uieventlist.put("DELETE",EventCodes.DELETE);
		uieventlist.put("SEARCH",EventCodes.SEARCH);
		uieventlist.put("SHARE",EventCodes.SHARE);
		uieventlist.put("TAG",EventCodes.TAG);
		
	}
	
	
	public static EventCodes getEvent(String command){
		
		EventCodes c = uieventlist.get(command);
		if(c == null){  return EventCodes.INVALID;}
		return c;
		
	}

}
