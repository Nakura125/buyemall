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

import it.unisa.bean.Ordine;
import it.unisa.bean.Prodotto;
import it.unisa.bean.Stato;
import it.unisa.bean.Tipo;
import it.unisa.interfaces.IBeanDao;

public class OrdineDAO implements IBeanDao<Ordine,Integer>{
	
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

	private static final String TABLE_NAME = "Ordine";
	

	@Override
	public synchronized void doSave(Ordine product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + OrdineDAO.TABLE_NAME
				+ " (idordine,prezzo, stato, data, username,idindirizzo) VALUES (?,?, ?, ?, ?,?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, product.getIdOrdine());
			preparedStatement.setFloat(2, product.getPrezzo());

			preparedStatement.setString(3, product.getStato().toString());
			preparedStatement.setDate(4, product.getData());
			preparedStatement.setString(5, product.getU().getUsername());
			preparedStatement.setInt(6, product.getSpedizione().getIdIndirizzo());

			preparedStatement.executeUpdate();

			connection.commit();
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
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + OrdineDAO.TABLE_NAME + " WHERE idOrdine = ?";

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
	public synchronized Ordine doRetrieveByKey(Integer code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Ordine bean = new Ordine();

		String selectSQL = "SELECT * FROM " + OrdineDAO.TABLE_NAME + " WHERE idOrdine = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdOrdine((rs.getInt("idordine")));
				bean.setStato(Stato.valueOf(rs.getString("stato")));
				bean.setPrezzo(rs.getFloat("prezzo"));
				bean.setData(rs.getDate("Data"));
				bean.setIndirizzo(new IndirizzoDAO().doRetrieveByKey(rs.getInt("idindirizzo")));

				bean.setU(new AccountDAO().doRetrieveByKey(rs.getString("username")));

				bean.addList(new ProdottoDAO().doRetrieveByKey(rs.getInt("idindirizzo")));
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
	
	public synchronized void UpdateStato(Stato st,Ordine ordine) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL ="UPDATE ordine\r\n"
				+ "SET stato = ?"
				+ "WHERE idOrdine = ? "
				+ "  AND username = '?';";
				
				try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(insertSQL);
					preparedStatement.setString(1, st.toString());
					preparedStatement.setInt(2, ordine.getIdOrdine());
					preparedStatement.setString(3, ordine.getU().getUsername());
					preparedStatement.executeUpdate();

					connection.commit();
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
	public synchronized Collection<Ordine> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
