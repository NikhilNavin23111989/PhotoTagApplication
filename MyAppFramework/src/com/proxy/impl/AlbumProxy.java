package com.proxy.impl;

import java.util.ArrayList;

import com.interfaces.model.Iproxy;

public class AlbumProxy implements Iproxy {
	
	private String albumname="ALbum proxy";
	private Object icon;
	private ArrayList<PhotoProxy> photolist;
	private ArrayList<Object>tags;
	

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return albumname;
	}

	@Override
	public Object getIcon() {
		// TODO Auto-generated method stub
		return icon;
	}

	@Override
	public Object getData() {
		// TODO Auto-generated method stub
		return photolist;
	}

	@Override
	public ArrayList<Object> getTags() {
		// TODO Auto-generated method stub
		return tags;
	}

	@Override
	public void addTag(Object tag) {
		// TODO Auto-generated method stub
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
		return "ALBUM";
	}

}
