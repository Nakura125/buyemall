package it.unisa.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class AdminDAO {
	private static DataSource ds;
	private static final Logger LOGGER = Logger.getLogger(AdminDAO.class.getName());
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/storage");

		} catch (NamingException e) {
			LOGGER.log(Level.INFO,"Error:",e);
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
