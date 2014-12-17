package com.services;

public class EngineServiceFactory {

	public static Iservice getService(EventCodes ev) {

		Iservice service = null;
		switch (ev) {
		case VIEW:
			service = new SampleService1();
			return service;
		case SEARCH:
			service = new SampleService1();
			return service;
		case SHARE:
			service = new SampleService1();
			return service;
		case DELETE:
			service = new SampleService1();
			return service;
		case ADD:
			service = new SampleService1();
			return service;
		case TAG:
			service=new SampleService1();
			return service;

		}

		return service;

	}

}
