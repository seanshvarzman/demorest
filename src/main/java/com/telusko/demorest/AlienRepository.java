package com.telusko.demorest;

import java.util.*;
import java.sql.*;

public class AlienRepository {

	Connection con=null;
	
	public AlienRepository()  {
		try {
		String username="root";
		String password="Sean0342591";
		Class.forName("com.mysql.jdbc.Driver");
		con= DriverManager.getConnection("jdbc:mysql://localhost:3306/restdb",username,password);
		} catch (Exception e) {
			System.out.print(e);
		}
	}
	
	

	public List<Alien> getAliens() throws SQLException{
		List <Alien> aliens=new ArrayList<Alien>();
		String sql= "select * from alien";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(sql);
		
		while(rs.next()) {
			Alien a=new Alien();
			a.setId(rs.getInt(1));
			a.setName(rs.getString(2));
			a.setPoints(rs.getInt(3));
			aliens.add(a);
		}
		return aliens;
	}
	
	
	
	public Alien getAlien(int id) throws SQLException  {
		String sql= "select * from alien where id="+id;
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(sql);
		Alien a=new Alien();
		if(rs.next()) {
			a.setId(rs.getInt(1));
			a.setName(rs.getString(2));
			a.setPoints(rs.getInt(3));
			}
		
		return a;
	}

	public void create(Alien a1) throws SQLException {
		String sql="insert into alien values (?,?,?)";
		PreparedStatement st=con.prepareStatement(sql);
		st.setInt(1, a1.getId());
		st.setString(2, a1.getName());
		st.setInt(3, a1.getPoints());
		st.executeUpdate();
		
	}
	
	public void update(Alien a1) throws SQLException {
		String sql="update alien set name=? , points=? where id=?";
		PreparedStatement st=con.prepareStatement(sql);
		st.setInt(3, a1.getId());
		st.setString(1, a1.getName());
		st.setInt(2, a1.getPoints());
		st.executeUpdate();
		
	}



	public void delete(int id) throws SQLException {
		String sql="delete from alien where id="+id;
		Statement st=con.createStatement();
		st.executeUpdate(sql);
		
	}
	
	
}
