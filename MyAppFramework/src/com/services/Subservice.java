package com.services;

import java.util.Properties;

import com.interfaces.model.Iproxy;

public interface Subservice {
	
	public Iproxy handleView(Properties payload);
	public Iproxy handleAdd(Properties payload);
	public Iproxy handleDelete(Properties payload);
	public Iproxy handleUpdate(Properties payload);
	public Iproxy handleSearch(Properties payload);
	public Iproxy handleTag(Properties payload);

}
