package shortener;

import java.sql.*;
import java.sql.DriverManager;

public class Logic {
	public Connection getConnection()throws IllegalAccessException,
    ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Aayush07@");
		return conn;
	}
	public int validationURL(String longURL) throws Exception {
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("select id from urlshortener where longurl=?");
		ps.setString(1, longURL);
		ResultSet rs = ps.executeQuery();
		int id=0;
		while(rs.next()) {
			id = rs.getInt(1);
		}
		rs.close();
		ps.close();
		conn.close();
		if(id!=0) {
			return id;
		}
		else return 0;
		
	}
	public String getshortURL(int id)throws Exception {
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("select shorturl from urlshortener where id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		String shortURL = new String("");
		while(rs.next()) {
			shortURL = rs.getString("shorturl");
		}
		rs.close();
		ps.close();
		conn.close();
		return shortURL;
	}
	public String getnewshortURL(String longURL) throws Exception{
		String shortURL = algo();
		int id = getID();
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement("insert into urlshortener values(?,?,?)");
		ps.setInt(1,id);
		ps.setString(2,longURL);
		ps.setString(3,shortURL);
		ps.executeUpdate();
		ps.close();
		conn.close();
		return shortURL;
	}
	public int getID() throws Exception {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select id from urlshortener ORDER BY id DESC");
		int id=0;
		while(rs.next()){
			id=rs.getInt(1);
			break;
		}
		rs.close();
		conn.close();
		return id+1;
	}
	public String algo() throws Exception {
		int id = getID();
		id= id-1;
		int i=4;
		String w =new String(""); 
		while(i>0){
		int temp=id%52;
		temp += 65;
		if(temp>90) {
			temp = 97;
		}
		else if(temp>122 && temp>=97) {
			temp = 65;
		}
		char b = (char)temp;
		w  += b;
		i--;
		id = id/52;
		}
		
  return w;
}
	 	public int checker(String shortURL) throws Exception {
	 		Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement("select shorturl from urlshortener where shorturl=?");
			ps.setString(1, shortURL);
			ResultSet rs = ps.executeQuery();
			String xyz= new String("");
			while(rs.next()) {
				xyz = rs.getString("shorturl");
			}
			rs.close();
			ps.close();
			conn.close();
			if(shortURL==xyz)
			return 1;
			else return 0;
	 	}
	 	public int join(String longURL, String shortURL) throws Exception{
			int id = getID();
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement("insert into urlshortener values(?,?,?)");
			ps.setInt(1,id);
			ps.setString(2,longURL);
			ps.setString(3,shortURL);
			int x = ps.executeUpdate();
			ps.close();
			conn.close();
			if(x!=0) {
				return 1;
			}
			else return 0;
	 	}
}
