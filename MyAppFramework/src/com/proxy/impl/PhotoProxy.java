package com.proxy.impl;

import java.util.ArrayList;

import com.interfaces.model.Iproxy;

public class PhotoProxy  implements Iproxy{
	
	
	private ArrayList<Object> tags;
	private Object icon;
	private Object image;
	private String title="photoproxy";
    private int pid;
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	@Override
	public Object getIcon() {
		// TODO Auto-generated method stub
		return icon;
	}

	public void setIcon(Object icon) {
		this.icon = icon;
	}

	public void setImage(Object image) {
		this.image = image;
	}

	@Override
	public Object getData() {
		// TODO Auto-generated method stub
		return image;
	}

	@Override
	public ArrayList<Object> getTags() {
		// TODO Auto-generated method stub
		return tags;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	@Override
	public void addTag(Object tag) {
		if(tags == null){
			tags = new ArrayList<>();
		}
		tags.add(tag);
	}

	@Override
	public void removeTag(Object tag) {
		// TODO Auto-generated method stub
		tags.remove(tag);
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "PHOTO";
	}

}
