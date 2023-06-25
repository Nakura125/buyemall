package it.unisa.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.bean.Amministratore;
import it.unisa.interfaces.IBeanDao;

public class AmministratoreDAO implements IBeanDao<Amministratore, String> {
	
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

	@Override
	public synchronized void doSave(Amministratore product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + AmministratoreDAO.TABLE_NAME
				+ " (idamministratore) VALUES (?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getUser().getUsername());
			

			preparedStatement.executeUpdate();

			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
	}
	
	public synchronized Amministratore Login(String username,String password) throws SQLException {
		Amministratore admin=new Amministratore();
		admin.setUser( new AccountDAO().Login(username, password));
		return admin;
	}

	@Override
	public synchronized boolean doDelete(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + AmministratoreDAO.TABLE_NAME + " WHERE idamministratore = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, code);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}

	@Override
	public synchronized Amministratore doRetrieveByKey(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Amministratore bean = new Amministratore();

		String selectSQL = "SELECT * FROM " + AmministratoreDAO.TABLE_NAME + " WHERE idamministratore = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setUser(new AccountDAO().doRetrieveByKey(rs.getString("username")));
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
		return bean;
	}

	@Override
	public synchronized Collection<Amministratore> doRetrieveAll(String order) throws SQLException {
		
		return null;
	}
	
}
