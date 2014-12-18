package com.db;

public class ApplicationInterfaceimpl {

	
	public static boolean createDB(String Path){
		
		return DatabaseAccess.createDB(Path);
	}
	
}
