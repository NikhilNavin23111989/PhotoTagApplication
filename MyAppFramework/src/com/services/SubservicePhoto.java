package com.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.db.DatabaseAccess;
import com.interfaces.model.Iproxy;
import com.proxy.impl.ListProxy;
import com.proxy.impl.PhotoProxy;

public class SubservicePhoto  implements Subservice{

	@Override
	public Iproxy handleView(Properties payload) {
		
		return new PhotoProxy();
	}

	@Override
	public Iproxy handleAdd(Properties payload) {
		PhotoProxy p = (PhotoProxy)payload.get("DATA");
		Properties tabledata = new Properties();
		tabledata.put("PID",p.getPid());
		tabledata.put("THUMNAIL",p.getIcon());
		tabledata.put("PATH",p.getData());
		try{
		DatabaseAccess.getInstance().addPhoto(tabledata);
		}
		catch(Exception e){
			
			System.out.println("DAO excetion:"+e.getMessage());
		}
		return p;
	}

	@Override
	public Iproxy handleDelete(Properties payload) {
		// delete the photo update all tables
		Iproxy p = (Iproxy)payload.get("DELETE");
		return p;
		
	}

	@Override
	public Iproxy handleUpdate(Properties payload) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iproxy handleSearch(Properties payload) {
		
		ArrayList<Iproxy> res = new ArrayList<>();
		String p = (String)payload.get("FILTER");
		Properties tabledata = new Properties();
		
	}
	
	private Iproxy searchPid(Properties payload){
		
		tabledata.put("PID",payload.get(p));
		ArrayList<Properties> r = null;
		try{
			r=DatabaseAccess.getInstance().getPhoto(tabledata);
			}
			catch(Exception e){
				
				System.out.println("DAO excetion:"+e.getMessage());
			}
		for(Properties photo : r){
			
			PhotoProxy picture = new PhotoProxy();
			picture.setPid((Integer)photo.get("PID"));
			picture.setImage((String)photo.get("PATH"));
			picture.setIcon((String)photo.get("THUMBNAIL"));
			res.add(picture);
		}
		return new ListProxy(res);
	}
	@Override
	public Iproxy handleTag(Properties payload) {
		PhotoProxy p = (PhotoProxy)payload.get("DATA");
		Properties tabledata = new Properties();
		tabledata.put("PID",p.getPid());
		tabledata.put("TAGS",p.getTags());
		try{
			DatabaseAccess.getInstance().addTag(tabledata);
			}
			catch(Exception e){
				
				System.out.println("DAO excetion:"+e.getMessage());
			}
		return p;
	}

}
