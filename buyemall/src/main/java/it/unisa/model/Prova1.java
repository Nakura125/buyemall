package it.unisa.model;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Prova1 {
	public static String op1(int key) {
		Connection con=null;
		Statement st=null;
		PreparedStatement pt=null;
		ResultSet rs=null;
		
		try {
			con=DBConnectionPool.getConnection();
			String sql="Select * From Pokemon where id=?";
			pt=con.prepareStatement(sql);
			pt.setInt(1, key);
			rs=pt.executeQuery();
			String f="";
			rs.next();
			if(!rs.isAfterLast()) {
			Integer id = rs.getInt("id");
			String forma=rs.getString("Forma");
			String sprite=rs.getString("Sprite");
			Integer generazione=rs.getInt("generazione");
			
			f=forma;
			}
			
			return f;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
}
