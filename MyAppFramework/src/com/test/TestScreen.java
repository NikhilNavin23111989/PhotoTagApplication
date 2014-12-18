package com.test;

import java.util.ArrayList;
import java.util.Properties;

import com.controller.UIcontroller;
import com.controller.UieventListener;
import com.db.ApplicationInterfaceimpl;
import com.interfaces.model.IlistProxy;
import com.interfaces.model.Iproxy;
import com.proxy.impl.ListProxy;
import com.proxy.impl.PhotoProxy;
import com.services.ServiceEngine;

public class TestScreen implements UieventListener {

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

		System.out.println("request completed:" + proxy.getTitle());
		if (proxy.getTitle() == "list proxy") {
			for (Iproxy p : (IlistProxy) proxy) {

				System.out.println("  " + p.getTitle());
			}
		}

	}

	public static void main(String[] args) {
		ApplicationInterfaceimpl
				.createDB("jdbc:sqlite:C://Users//Lenovo//Documents//understanding//myjava//Application//db//mydb.db");

		TestScreen t = new TestScreen();

		startServiceEngine();
		addPhotosAndTags(t);

		System.out.println("calling search");
		TestScreen t1 = new TestScreen();
		callSearchPhotoPID(t1);

		callSearchTag();

	}

	private static void callSearchPhotoPID(TestScreen t1) {
		Properties p = new Properties();
		p.put("ecode", "SEARCH");
		p.put("TYPE", "PHOTO");
		p.put("id", t1.toString() + "1");
		p.put("FILTER", "PID");
		p.put("PID", 0);
		if (t1.sendEvent(p)) {
			UIcontroller.getInstance().waitforEvent(t1.toString() + "1", t1);
		}

	}

	private static void addPhotosAndTags(TestScreen t) {
		for (int i = 0; i < 100; i++) {

			// TODO Auto-generated method stub
			// System.out.println("sending requrst....");
			PhotoProxy prox = new PhotoProxy();
			prox.setPid(i);
			prox.setIcon("//thum//a" + i + "/b");
			prox.setImage("//path//a" + i + "/b");
			prox.addTag("abc_" + i);
			prox.addTag("xyz_" + i);
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
			pr2.put("id", t.toString() + "tag" + i);
			if (t.sendEvent(pr2)) {
				UIcontroller.getInstance().waitforEvent(
						t.toString() + "tag" + i, t);
			}

		}

	}

	private static void startServiceEngine() {
		Thread service = new Thread(ServiceEngine.IDLE);
		service.start();

	}

	private static void callSearchTag() {
		TestScreen t2 = new TestScreen();
		Properties p1 = new Properties();
		p1.put("ecode", "SEARCH");
		p1.put("TYPE", "PHOTO");
		p1.put("id", t2.toString() + "1");
		p1.put("FILTER", "TAG");
		ArrayList<String> tgs = new ArrayList<>();
		tgs.add("abc_1");
		tgs.add("abc_2");
		p1.put("TAGS", tgs);
		if (t2.sendEvent(p1)) {
			UIcontroller.getInstance().waitforEvent(t2.toString() + "1", t2);
		}
	}

}
