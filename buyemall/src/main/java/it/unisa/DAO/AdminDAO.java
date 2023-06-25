package it.unisa.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class AdminDAO {
	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/storage");

		} catch (NamingException e) {
			System.err.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "Amministratore";
	
	
	public synchronized boolean doRetrieveByKey(String code) throws SQLException {
		
		
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM " + AdminDAO.TABLE_NAME + " WHERE idamministratore = ?";
		String u=null;
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				u=rs.getString("idamministratore");
				
			}
			
			
		} finally {
			try {
				if (preparedStatement != null)
					
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return u!=null;
		
	}
}
