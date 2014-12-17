package com.services;

import java.util.Properties;

import com.interfaces.model.Iproxy;
import com.proxy.impl.AlbumProxy;

public class SubserviceAlbum implements Subservice {

	@Override
	public Iproxy handleView(Properties payload) {
		// TODO Auto-generated method stub
		return new AlbumProxy();
	}

	@Override
	public Iproxy handleAdd(Properties payload) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iproxy handleDelete(Properties payload) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iproxy handleUpdate(Properties payload) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iproxy handleSearch(Properties payload) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iproxy handleTag(Properties payload) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
