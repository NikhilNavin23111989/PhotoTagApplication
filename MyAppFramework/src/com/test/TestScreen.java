package com.test;

import java.util.Properties;

import com.controller.UIcontroller;
import com.controller.UieventListener;
import com.interfaces.model.IlistProxy;
import com.interfaces.model.Iproxy;
import com.proxy.impl.ListProxy;
import com.proxy.impl.PhotoProxy;
import com.services.ServiceEngine;

public class TestScreen  implements UieventListener{

	@Override
	public Properties getUIEventPayload() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean sendEvent(Properties ev) {
		

		return UIcontroller.getInstance().sendEvent(ev);
		
	}

	@Override
	public void RequestCompleted(Iproxy proxy) {
		
		System.out.println("request completed:"+proxy.getTitle());
		if(proxy.getTitle()=="list proxy"){
			System.out.println("it is a list");
			for(Iproxy p : (IlistProxy)proxy){
				
				System.out.println("  "+p.getTitle());
			}
		}
		
	}
	
	
	public static void main(String[] args) {

		Thread service = new Thread(ServiceEngine.IDLE);
		service.start();
		TestScreen t = new TestScreen();

		for (int i = 0; i < 100; i++) {

			// TODO Auto-generated method stub
			//System.out.println("sending requrst....");
			PhotoProxy prox = new PhotoProxy();
			prox.setPid(i);
			prox.setIcon("//thum//a"+i+"/b");
			prox.setImage("//path//a"+i+"/b");
            prox.addTag("abc_"+i);
            prox.addTag("xyz_"+i);
			Properties pr = new Properties();
			pr.put("ecode", "ADD");
			pr.put("TYPE", "PHOTO");
			pr.put("id", t.toString() + "1");
			pr.put("DATA", prox);
			if (t.sendEvent(pr)) {
				UIcontroller.getInstance().waitforEvent(t.toString() + "1", t);
			}
			Properties pr2 = new Properties();
			pr2.put("ecode", "TAG");
			pr2.put("DATA", prox);
			pr2.put("TYPE", "PHOTO");
			pr2.put("id",t.toString() + "tag"+i);
			if (t.sendEvent(pr2)) {
				UIcontroller.getInstance().waitforEvent(t.toString() + "tag"+i, t);
			}
			

	}
		System.out.println("calling search");
	    TestScreen t1 = new TestScreen();
		Properties p = new Properties();
		p.put("ecode", "SEARCH");
		p.put("TYPE", "PHOTO");
		p.put("id", t1.toString() + "1");
		p.put("FILTER","PID");
		p.put("PID",0);
		if (t1.sendEvent(p)) {
			UIcontroller.getInstance().waitforEvent(t1.toString() + "1", t1);
		}
		
		


	}



}
