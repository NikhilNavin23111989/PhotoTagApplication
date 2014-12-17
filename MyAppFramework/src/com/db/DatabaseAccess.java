package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class DatabaseAccess implements DbConnection {

	private static DatabaseAccess dbconnection = new DatabaseAccess();
	private static String db="jdbc:sqlite:C://Users//Lenovo//Documents//understanding//myjava//Application//db//mydb.db";
	private Connection connection ;
	
	private static boolean created = false;
	
	private PreparedStatement addphoto,getphoto,addtag;
	
	{
		
		
	}

	private DatabaseAccess(){
		try{
		init();
		}
		catch(Exception e){
			
			System.out.println("db exception:"+e.getMessage());
		}
	}
	
	private void init() throws Exception{
		db="jdbc:sqlite:C://Users//Lenovo//Documents//understanding//myjava//Application//db//mydb.db";
		System.out.println("init called :"+db);
		connection = DriverManager.getConnection(db);
		if(! created){
			Statement s = connection.createStatement();
			s.executeUpdate("CREATE TABLE photo_  (pid_ int,thumnail_ varchar(200),path_ varchar(200))");
			s.executeUpdate("CREATE TABLE tag_  (imageid_  int,tagname_ text,FOREIGN KEY(imageid_) REFERENCES photo_(pid_))");
			System.out.println("created table succesfully");
			}
		createpreparedstatement();
	}

	private void createpreparedstatement() throws Exception {
		
		addphoto = connection.prepareStatement("INSERT INTO photo_ (pid_ ,thumnail_ ,path_ ) VALUES (?,?,?)");
		addtag = connection.prepareStatement("INSERT INTO tag_ (imageid_ ,tagname_ ) VALUES (?,?)");
		getphoto= connection.prepareStatement("SELECT *  FROM photo_  WHERE pid_ > ?");
	}

	public static DatabaseAccess getInstance() {
		
		return dbconnection;
	}
	
	

	@Override
	public void addPhoto(Properties p) throws Exception {
		Integer pid = (Integer) p.get("PID");
		String thumnail = (String) p.get("THUMNAIL");
		String path = (String) p.get("PATH");
		addphoto.setInt(1, pid);
		addphoto.setString(2, thumnail);

		addphoto.setString(3, path);

		addphoto.execute();
		System.out.println("added photo succesfully");

	}

	@Override
	public void addAlbum(Properties p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTag(Properties p) throws Exception {
		Integer pid = (Integer) p.get("PID");
		ArrayList<String> tags = (ArrayList<String>)p.get("TAGS");
		for(String tagname : tags){
			
			addtag.setInt(1,pid);
			addtag.setString(2,tagname);
			addtag.execute();
		}
		
		
		System.out.println("added tags succesfully");
		
	}

	@Override
	public ArrayList<Properties> getPhoto(Properties p) throws Exception{
		
		// handle cases for namesearch,pid search
		ArrayList<Properties> result = new ArrayList<Properties>();
		int pid = (Integer)p.get("PID");
		getphoto.setInt(1, pid);
		
		ResultSet rs=getphoto.executeQuery();
		while(rs.next())
	      {
	        Properties retp = new Properties();
	        retp.put("PID",rs.getInt("pid_"));// read the result set
	       retp.put("PATH",rs.getString("path_"));
	       retp.put("THUMBNAIL",rs.getString("thumnail_"));
	       result.add(retp);
	        
	      }
		


	      
	      return result;
	}



	
	
}
