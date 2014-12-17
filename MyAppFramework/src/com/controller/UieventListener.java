package com.controller;
import java.util.Properties;

import com.interfaces.model.Iproxy;


public interface UieventListener {
	
	public Properties getUIEventPayload();
	
	public boolean sendEvent(Properties ev);
	
	public void RequestCompleted(Iproxy proxy);

}
