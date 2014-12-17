package com.services;

public class SubserviceFactory {
	
	
	private SubserviceFactory(){}
	
	public static Subservice getSubService(String type){
		
		if(type == "PHOTO"){
			
			return new SubservicePhoto();
		}
		if(type=="ALBUM"){
			
			return new SubserviceAlbum();
		}
		
		return null;
	}

}
