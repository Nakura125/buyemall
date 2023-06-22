package it.unisa.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.bean.MetodiPagamento;
import it.unisa.interfaces.IBeanDao;

public class MetodiPagamentoDAO implements IBeanDao<MetodiPagamento, String>{

	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/storage");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private  static final String TABLE_NAME = "metodi_pagamento";
	
	
	@Override
	public synchronized void doSave(MetodiPagamento product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + MetodiPagamentoDAO.TABLE_NAME
				+ " (username,tipo_pagamento, numero_carta, data_scadenza, cvc) VALUES (?,?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getUsername().getUsername());
			preparedStatement.setString(2,product.getTipo_pagamento());
			preparedStatement.setString(3, product.getNumero_carta());
			preparedStatement.setDate(4, product.getData_scadenza());
			preparedStatement.setString(5, product.getCvc());
			

			preparedStatement.executeUpdate();

			//connection.commit();
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

	@Override
	public synchronized boolean doDelete(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + MetodiPagamentoDAO.TABLE_NAME + " WHERE numero_carta = ?";

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
	public synchronized MetodiPagamento doRetrieveByKey(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		MetodiPagamento bean = new MetodiPagamento();

		String selectSQL = "SELECT * FROM " + MetodiPagamentoDAO.TABLE_NAME + " WHERE numero_carta = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setTipo_pagamento(rs.getString("tipo_pagamento"));
				bean.setNumero_carta(rs.getString("numero_carta"));
				bean.setData_scadenza(rs.getDate("data_scadenza"));
				bean.setCvc(rs.getString("cvc"));
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
	
	
	public synchronized MetodiPagamento doRetrieveByKey(String code,String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		MetodiPagamento bean = new MetodiPagamento();

		String selectSQL = "SELECT * FROM " + MetodiPagamentoDAO.TABLE_NAME + " WHERE numero_carta = ? and username=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);
			preparedStatement.setString(2, username);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setTipo_pagamento(rs.getString("tipo_pagamento"));
				bean.setNumero_carta(rs.getString("numero_carta"));
				bean.setData_scadenza(rs.getDate("data_scadenza"));
				bean.setCvc(rs.getString("cvc"));
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
	
	public synchronized List<MetodiPagamento> doRetrieveByUsername(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		List<MetodiPagamento> ls=new LinkedList<>();

		String selectSQL = "SELECT * FROM " + MetodiPagamentoDAO.TABLE_NAME + " WHERE username = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				MetodiPagamento bean = new MetodiPagamento();
				bean.setTipo_pagamento(rs.getString("tipo_pagamento"));
				bean.setNumero_carta(rs.getString("numero_carta"));
				bean.setData_scadenza(rs.getDate("data_scadenza"));
				bean.setCvc(rs.getString("cvc"));
				
				ls.add(bean);
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
		return ls;
	}

	@Override
	public synchronized Collection<MetodiPagamento> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<MetodiPagamento> products = new LinkedList<MetodiPagamento>();

		String selectSQL = "SELECT * FROM " + MetodiPagamentoDAO.TABLE_NAME + " ORDER BY ?";

		if (order == null || order.equals("")) {
			order="numero_carta";
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, order);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				MetodiPagamento bean = new MetodiPagamento();

				bean.setTipo_pagamento(rs.getString("tipo_pagamento"));
				bean.setNumero_carta(rs.getString("numero_carta"));
				bean.setData_scadenza(rs.getDate("data_scadenza"));
				bean.setCvc(rs.getString("cvc"));
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
	
	
	public synchronized Collection<MetodiPagamento> doRetrieveAByUsername(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<MetodiPagamento> products = new LinkedList<MetodiPagamento>();

		String selectSQL = "SELECT * FROM " + MetodiPagamentoDAO.TABLE_NAME +" where username= ?";


		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				MetodiPagamento bean = new MetodiPagamento();

				
				bean.setTipo_pagamento(rs.getString("tipo_pagamento"));
				bean.setNumero_carta(rs.getString("numero_carta"));
				bean.setData_scadenza(rs.getDate("data_scadenza"));
				bean.setCvc(rs.getString("cvc"));
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
