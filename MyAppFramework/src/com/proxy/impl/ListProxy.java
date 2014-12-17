package com.proxy.impl;

import java.util.ArrayList;
import java.util.Iterator;

import com.interfaces.model.IlistProxy;
import com.interfaces.model.Iproxy;

public class ListProxy implements IlistProxy{
	
	
	private ArrayList<Iproxy> contents;
    private String title="list proxy";
    private int currentIndex=0;
    
    public ListProxy(ArrayList<Iproxy> con){
    	
    	contents = con;
    }
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "list proxy";
	}

	@Override
	public Object getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getData() {
		// TODO Auto-generated method stub
		return contents.get(currentIndex).getIcon();
	}

	@Override
	public ArrayList<Object> getTags() {
		// TODO Auto-generated method stub
		return contents.get(currentIndex).getTags();
	}

	@Override
	public void addTag(Object tag) {
		contents.get(currentIndex).addTag(tag);
		
	}

	@Override
	public void removeTag(Object tag) {
		contents.get(currentIndex).removeTag(tag);
		
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return contents.get(currentIndex).getType();
	}
	@Override
	public Iterator<Iproxy> iterator() {
		System.out.println("iterator called:"+contents.size());
		return contents.iterator();
	}

}
