package com.interfaces.model;

import java.util.ArrayList;

public interface Iproxy {
	
	public String getTitle();
	public Object getIcon();
	public Object getData();
	public ArrayList<Object> getTags();
	public void addTag(Object tag);
	public void removeTag(Object tag);
	public String getType();

}
