package it.unisa.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.bean.Account;
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
				+ " (prezzo, stato, data, username,idindirizzo) VALUES (?, ?, ?, ?,?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setFloat(1, product.getPrezzo());

			preparedStatement.setString(2, product.getStato().toString());
			preparedStatement.setDate(3, product.getData());
			preparedStatement.setString(4, product.getU().getUsername());
			preparedStatement.setInt(5, product.getSpedizione().getIdIndirizzo());

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
	
	public synchronized void doSaveComposto(Account a,Ordine product,Prodotto pd) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO composto"
				+ " (idordine,username,idProdotto) VALUES (?,?,?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, product.getIdOrdine());
			preparedStatement.setString(2, a.getUsername());
			preparedStatement.setInt(3, pd.getIdProdotto());

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
	
	public static List<Prodotto> recoverProdotti(Ordine product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		List<Prodotto> products = new LinkedList<>();

		//TODO
		String selectSQL = "select * from composto where idordine= ?";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, product.getIdOrdine());
			

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Prodotto bean=new Prodotto();
				
				bean.setIdProdotto((rs.getInt("idProdotto")));
				bean=new ProdottoDAO().doRetrieveByKey(bean.getIdProdotto());
				
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
	
	public synchronized Integer doSaveGenerator(Ordine product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet generatedKeys = null;
		Integer generatedId = null;
		String insertSQL = "INSERT INTO " + OrdineDAO.TABLE_NAME
				+ " (prezzo, stato, data, username,idindirizzo) VALUES (?, ?, ?, ?,?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL,Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setFloat(1, product.getPrezzo());

			preparedStatement.setString(2, product.getStato().toString());
			preparedStatement.setDate(3, product.getData());
			preparedStatement.setString(4, product.getU().getUsername());
			preparedStatement.setInt(5, product.getSpedizione().getIdIndirizzo());

			preparedStatement.executeUpdate();

	        generatedKeys = preparedStatement.getGeneratedKeys();
	        if (generatedKeys.next()) {
	             generatedId = generatedKeys.getInt(1);
	            // Puoi utilizzare l'id generato per ulteriori operazioni
	            System.out.println("Id dell'ordine inserito: " + generatedId);
	        }
			//connection.commit();
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
				
				bean.setPo(new PagamentoOrdineDAO().doRetrieveByKey(bean.getIdOrdine()));
				bean.addList(OrdineDAO.recoverProdotti(bean));

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
	
	
	public synchronized List<Ordine> doRetrieveByUsername(Account code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		List<Ordine> ls = new LinkedList<Ordine>();

		String selectSQL = "SELECT * FROM " + OrdineDAO.TABLE_NAME + " WHERE username = ? order by `data` DESC";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code.getUsername());

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				Ordine bean = new Ordine();
				bean.setIdOrdine((rs.getInt("idordine")));
				bean.setStato(Stato.valueOf(rs.getString("stato")));
				bean.setPrezzo(rs.getFloat("prezzo"));
				bean.setData(rs.getDate("Data"));
				bean.setIndirizzo(new IndirizzoDAO().doRetrieveByKey(rs.getInt("idindirizzo")));

				bean.setU(new AccountDAO().doRetrieveByKey(rs.getString("username")));
				
				bean.setPo(new PagamentoOrdineDAO().doRetrieveByKey(bean.getIdOrdine()));
				bean.addList(OrdineDAO.recoverProdotti(bean));
				
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
	
	public synchronized void UpdateStato(Stato st,Ordine ordine) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL ="UPDATE ordine "
				+ "SET stato = ? "
				+ "WHERE idOrdine = ? "
				+ "  AND username = ?; ";
				
				try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(insertSQL);
					preparedStatement.setString(1, st.toString());
					preparedStatement.setInt(2, ordine.getIdOrdine());
					preparedStatement.setString(3, ordine.getU().getUsername());
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
	public synchronized Collection<Ordine> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
