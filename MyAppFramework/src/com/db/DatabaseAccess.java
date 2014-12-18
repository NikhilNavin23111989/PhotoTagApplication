package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class DatabaseAccess implements DbConnection {

	private static DatabaseAccess dbconnection = new DatabaseAccess();
	private static String db="";
	private static Connection connection ;
	
	private static boolean created = false;
	
	private static PreparedStatement addphoto,getphoto,addtag,getphototag;
	
	{
		
		
	}

	private DatabaseAccess(){}
	
	
	public  static boolean createDB(String Path){
		
		if(!created){
			try {
				db=Path;//"jdbc:sqlite:C://Users//Lenovo//Documents//understanding//myjava//Application//db//mydb.db";
				init();
				created=true;
				return true;
				
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		
		return false;
	}
	
	private  static void init() throws Exception{
		
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

	private static void createpreparedstatement() throws Exception {
		
		addphoto = connection.prepareStatement("INSERT INTO photo_ (pid_ ,thumnail_ ,path_ ) VALUES (?,?,?)");
		addtag = connection.prepareStatement("INSERT INTO tag_ (imageid_ ,tagname_ ) VALUES (?,?)");
		getphoto= connection.prepareStatement("SELECT *  FROM photo_  WHERE pid_ = ?");
		//getphototag= connection.prepareStatement("SELECT pid_  FROM (SELECT *  FROM photo_ INNER JOIN tag_   ON photo_.pid_ = tag_.imageid_) WHERE tagname_ IN []");
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
		//System.out.println("added photo succesfully");

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
		
		
		//System.out.println("added tags succesfully");
		
	}

	@Override
	public ArrayList<Properties> getPhoto(Properties p) throws Exception{
		
		// handle cases for namesearch,pid search
		ArrayList<Properties> result = new ArrayList<Properties>();
		int pid = (Integer)p.get("PID");
		getphoto.setInt(1,0);
		
		ResultSet rs=getphoto.executeQuery();
		int c =0;
		
		while(rs.next())
	      {
			c=c+1;
	        Properties retp = new Properties();
	        retp.put("PID",rs.getInt("pid_"));// read the result set
	       retp.put("PATH",rs.getString("path_"));
	       retp.put("THUMBNAIL",rs.getString("thumnail_"));
	       result.add(retp);
	        
	      }
		
        

	      
	      return result;
	}

	@Override
	public ArrayList<Properties> getPhotoforTags(Properties p) throws Exception {
		// handle cases for namesearch,pid search
		ArrayList<Properties> result = new ArrayList<Properties>();
		ArrayList<String> tags = (ArrayList<String>) p.get("TAGS");
		String query = "SELECT * FROM photo_ WHERE photo_.pid_  IN (SELECT imageid_ FROM tag_  WHERE tag_.tagname_ IN  ( "+makePlaceholders(tags.size())+") )" ;
		String inlists = "( ";


		inlists=inlists+" )";
		PreparedStatement st = connection.prepareStatement(query);
		for (int i = 0; i < tags.size(); i++) {

			st.setString(i+1, tags.get(i));
			
		}
		//query.setString(1,"'abc_1','abc_2'");
		ResultSet rs = st.executeQuery();
		int c=0;
		while (rs.next()) {
			c=c+1;
			Properties retp = new Properties();
			retp.put("PID", rs.getInt("pid_"));// read the result set
			System.out.println("pid: "+rs.getString("pid_"));
			result.add(retp);
		}

		System.out.println("count "+c);
		return result;
	}
	
	
	String makePlaceholders(int len) {
	    if (len < 1) {
	        // It will lead to an invalid query anyway ..
	        throw new RuntimeException("No placeholders");
	    } else {
	        StringBuilder sb = new StringBuilder(len * 2 - 1);
	        sb.append("?");
	        for (int i = 1; i < len; i++) {
	            sb.append(",?");
	        }
	        return sb.toString();
	    }
	}
	
	
}
