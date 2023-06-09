package it.unisa.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import it.unisa.bean.Indirizzo;

import it.unisa.interfaces.IBeanDao;

public class IndirizzoDAO implements IBeanDao<Indirizzo,Integer>{
	private static final Logger LOGGER = Logger.getLogger(IndirizzoDAO.class.getName());

	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/storage");

		} catch (NamingException e) {
			LOGGER.log(Level.INFO,"Error:",e);
		}
	}

	private static final String TABLE_NAME = "indirizzo";
	
	
	@Override
	public synchronized void doSave(Indirizzo product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + IndirizzoDAO.TABLE_NAME
				+ " (idIndirizzo,VIA, Citta, provincia, n_civico) VALUES (?,?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, product.getIdIndirizzo());
			preparedStatement.setString(2, product.getVia());
			preparedStatement.setString(3, product.getCitta());
			preparedStatement.setString(4, product.getProvincia());
			preparedStatement.setString(5, product.getN_civico());

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
	
	public synchronized Integer doSaveGenerator(Indirizzo product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet generatedKeys = null;
		Integer generatedId = null;
		String insertSQL = "INSERT INTO " + IndirizzoDAO.TABLE_NAME
				+ " (idIndirizzo,VIA, Citta, provincia, n_civico) VALUES (?,?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, product.getIdIndirizzo());
			preparedStatement.setString(2, product.getVia());
			preparedStatement.setString(3, product.getCitta());
			preparedStatement.setString(4, product.getProvincia());
			preparedStatement.setString(5, product.getN_civico());

			preparedStatement.executeUpdate();

	        generatedKeys = preparedStatement.getGeneratedKeys();
	        if (generatedKeys.next()) {
	             generatedId = generatedKeys.getInt(1);
	            
	        }
			
		} finally {
			try {
				if (generatedKeys != null)
	                generatedKeys.close();
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return generatedId;
	}

	@Override
	public synchronized boolean doDelete(Integer code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + IndirizzoDAO.TABLE_NAME + " WHERE idindirizzo = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

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
	public synchronized Indirizzo doRetrieveByKey(Integer code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Indirizzo bean = new Indirizzo();

		String selectSQL = "SELECT * FROM " + IndirizzoDAO.TABLE_NAME + " WHERE idindirizzo = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdIndirizzo((rs.getInt("idIndirizzo")));
				bean.setVia(rs.getString("via"));
				bean.setCitta(rs.getString("citta"));
				bean.setProvincia(rs.getString("provincia"));
				bean.setN_civico(rs.getString("n_civico"));
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
	public synchronized Collection<Indirizzo> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Indirizzo> products = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + IndirizzoDAO.TABLE_NAME + " ORDER BY ? Limit 100";

		if (order == null || order.equals("")) {
			order="idindirizzo";
		}
		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, order);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Indirizzo bean = new Indirizzo();

				bean.setIdIndirizzo(rs.getInt("idIndirizzo"));
				bean.setVia(rs.getString("via"));
				bean.setCitta(rs.getString("citta"));
				bean.setProvincia(rs.getString("provincia"));
				bean.setN_civico(rs.getString("n_civico"));
				products.add(bean);
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
		return products;
	}
	
}
