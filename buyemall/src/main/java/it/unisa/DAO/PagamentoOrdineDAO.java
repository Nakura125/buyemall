package it.unisa.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.bean.MetodiPagamento;
import it.unisa.bean.PagamentoOrdine;
import it.unisa.interfaces.IBeanDao;

public class PagamentoOrdineDAO implements IBeanDao<PagamentoOrdine,Integer>{

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

	private static final String TABLE_NAME = "pagamento_ordine";
	
	@Override
	public synchronized void doSave(PagamentoOrdine product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + PagamentoOrdineDAO.TABLE_NAME
				+ " (idordine,tipo, numero_carta, data, cvc,username) VALUES (?,?, ?, ?, ?,?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1,product.getOrdine().getIdOrdine());
			preparedStatement.setString(2,product.getTipo());
			preparedStatement.setString(3, product.getNumero_carta());
			preparedStatement.setDate(4, product.getData());
			preparedStatement.setString(5, product.getCvc());
			preparedStatement.setString(6, product.getUsername().getUsername());
			

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

	@Override
	public synchronized boolean doDelete(Integer code) throws SQLException {
		
		return false;
	}

	@Override
	public synchronized PagamentoOrdine doRetrieveByKey(Integer code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		PagamentoOrdine bean = new PagamentoOrdine();

		String selectSQL = "SELECT * FROM " + PagamentoOrdineDAO.TABLE_NAME + " WHERE idordine = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setTipo(rs.getString("tipo"));
				bean.setNumero_carta(rs.getString("numero_carta"));
				bean.setData(rs.getDate("data"));
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

	public synchronized Collection<PagamentoOrdine> doRetrieveByOrdine(Integer idordine) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<PagamentoOrdine> products = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + PagamentoOrdineDAO.TABLE_NAME +" where idordine= ?";


		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idordine);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PagamentoOrdine bean = new PagamentoOrdine();

				
				bean.setTipo(rs.getString("tipo_pagamento"));
				bean.setNumero_carta(rs.getString("numero_carta"));
				bean.setData(rs.getDate("data"));
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
	
	@Override
	public synchronized Collection<PagamentoOrdine> doRetrieveAll(String order) throws SQLException {
		
		return null;
	}

}
