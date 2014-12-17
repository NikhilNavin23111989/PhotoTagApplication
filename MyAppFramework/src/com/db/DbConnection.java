package com.db;

import java.util.ArrayList;
import java.util.Properties;

import com.interfaces.model.Iproxy;
import com.proxy.impl.AlbumProxy;
import com.proxy.impl.PhotoProxy;

public interface DbConnection {
	
	
	public void addPhoto(Properties p) throws Exception;
	
	public void addAlbum(Properties p);
	
	public void addTag(Properties p)throws Exception;
	
	public ArrayList<Properties> getPhoto(Properties p) throws Exception;
	

}
