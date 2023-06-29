package it.unisa.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.bean.Account;
import it.unisa.bean.Ordine;
import it.unisa.bean.Prodotto;
import it.unisa.bean.Sprites;
import it.unisa.bean.Stato;

import it.unisa.interfaces.IBeanDao;

public class OrdineDAO implements IBeanDao<Ordine,Integer>{
	private static final Logger LOGGER = Logger.getLogger(OrdineDAO.class.getName());

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
				+ " (idordine,username,idProdotto,nome,prezzo,idSprite) VALUES (?,?,?,?,?,?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, product.getIdOrdine());
			preparedStatement.setString(2, a.getUsername());
			preparedStatement.setInt(3, pd.getIdProdotto());
			preparedStatement.setString(4, pd.getNome());
			preparedStatement.setFloat(5, pd.getPrezzo());
			preparedStatement.setInt(6, pd.getSprites().get(0).getIdSprites());
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
	
	public static List<Prodotto> recoverProdotti(Ordine product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		List<Prodotto> products = new LinkedList<>();

		
		String selectSQL = "select * from composto where idordine= ?";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, product.getIdOrdine());
			

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Prodotto bean=new Prodotto();
				
				bean.setIdProdotto((rs.getInt("idProdotto")));
				bean.setNome(rs.getString("nome"));
				bean.setPrezzo((rs.getFloat("prezzo")));
				bean.addSprites(new SpritesDAO().doRetrieveByKey(rs.getInt("idSprite")));
				bean.addSprites(Sprites.nullSprites());
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

		List<Ordine> ls = new LinkedList<>();

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
		 List<Ordine> ordini = new ArrayList<>();

		    Connection connection = null;
		    PreparedStatement preparedStatement = null;

		    String selectSQL = "SELECT * FROM " + TABLE_NAME;

		    try {
		        connection = ds.getConnection();
		        preparedStatement = connection.prepareStatement(selectSQL);
		        ResultSet rs = preparedStatement.executeQuery();

		        while (rs.next()) {
		            Ordine ordine = new Ordine();
		            ordine.setIdOrdine(rs.getInt("idOrdine"));
		            ordine.setStato(Stato.valueOf(rs.getString("stato")));
		            ordine.setPrezzo(rs.getFloat("prezzo"));
		            ordine.setData(rs.getDate("Data"));
		            ordine.setIndirizzo(new IndirizzoDAO().doRetrieveByKey(rs.getInt("idindirizzo")));
		            ordine.setU(new AccountDAO().doRetrieveByKey(rs.getString("username")));
		            ordine.addList(new ProdottoDAO().doRetrieveByKey(rs.getInt("idindirizzo")));

		            ordini.add(ordine);
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

		    return ordini;
	}
	
	public List<Ordine> visualizzaOrdiniPerStato(Stato stato, String order) throws SQLException {
	    List<Ordine> ordini = new ArrayList<>();

	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE stato = ?";

	    if (order != null && !order.isEmpty()) {
	        selectSQL += " ORDER BY stato";
	        if (order.equalsIgnoreCase("completati")) {
	            selectSQL += " DESC";
	        }
	    }

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(selectSQL);
	        preparedStatement.setString(1, stato.toString());
	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            Ordine ordine = new Ordine();
	            ordine.setIdOrdine(rs.getInt("idOrdine"));
	            ordine.setStato(Stato.valueOf(rs.getString("stato")));
	            ordine.setPrezzo(rs.getFloat("prezzo"));
	            ordine.setData(rs.getDate("Data"));
	            ordine.setIndirizzo(new IndirizzoDAO().doRetrieveByKey(rs.getInt("idindirizzo")));
	            ordine.setU(new AccountDAO().doRetrieveByKey(rs.getString("username")));
	            ordine.addList(new ProdottoDAO().doRetrieveByKey(rs.getInt("idindirizzo")));

	            ordini.add(ordine);
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

	    return ordini;
	}
	
	
	public synchronized List<Ordine> doRetrieveByData(Date data1,Date data2) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		List<Ordine> ls = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + OrdineDAO.TABLE_NAME + " WHERE data >= ? AND data<=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setDate(1, data1);
			preparedStatement.setDate(2, data2);
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
	

}
