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

import it.unisa.bean.Ordine;
import it.unisa.bean.Prodotto;
import it.unisa.bean.Stato;
import it.unisa.bean.Tipo;
import it.unisa.interfaces.IBeanDao;

public class ProdottoDAO implements IBeanDao<Prodotto,Integer>{
	
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

	private static final String TABLE_NAME = "Prodotto";
	

	@Override
	public synchronized void doSave(Prodotto product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProdottoDAO.TABLE_NAME
				+ " (idProdotti,tipo, quantita, nome, descrizione,prezzo,generazione) VALUES (?,?, ?, ?, ?,?,?,?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, product.getIdProdotto());
			preparedStatement.setString(2, product.getTipo().toString());
			preparedStatement.setInt(3, product.getQuantita());
			preparedStatement.setString(4, product.getNome());
			preparedStatement.setString(5, product.getDescrizione());
			preparedStatement.setFloat(6,product.getPrezzo() );
			preparedStatement.setInt(7,product.getGenerazione() );
			preparedStatement.setString(8,product.getNazionalita() );

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
	
	
	public synchronized void UpdateQuantita(Integer it,Prodotto pr) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL ="UPDATE prodotti"
				+ "SET Quantita = ? "
				+ "WHERE idProdotti = ? ";
				
				
				try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(insertSQL);
					preparedStatement.setInt(1, it);
					preparedStatement.setInt(2, pr.getIdProdotto());
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
	
	public synchronized void UpdateVisit(Integer it,Prodotto pr) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL ="UPDATE prodotti"
				+ "SET visitato = ? "
				+ "WHERE idProdotti = ? ";
				
				
				try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(insertSQL);
					preparedStatement.setInt(1, it);
					preparedStatement.setInt(2, pr.getIdProdotto());
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public synchronized Prodotto doRetrieveByKey(Integer code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Prodotto bean = new Prodotto();

		String selectSQL = "SELECT * FROM " + ProdottoDAO.TABLE_NAME + " WHERE idProdotti = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setIdProdotto((rs.getInt("idProdotti")));
				bean.setTipo(Tipo.valueOf(rs.getString("tipo")));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setNome(rs.getString("Nome"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setPrezzo(rs.getFloat("Prezzo"));
				bean.setGenerazione(rs.getInt("generazione"));

				bean.setVisitato(rs.getInt("visitato"));

				bean.addSprites(new SpritesDAO().doRetrieveByProdotto(bean));

				bean.setNazionalita(rs.getString("Nazionalita"));
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
	
	public synchronized Collection<Prodotto> RetrieveByFilters(String nome,Integer generazione,Tipo tipo,Float  prezzo,String nazione) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<Prodotto> products = new LinkedList<Prodotto>();
		
		if(generazione ==null&&tipo==null&&prezzo ==null&&nazione==null)
			return doRetrieveAllClient("visitato");

		String selectSQL = "SELECT * FROM " + ProdottoDAO.TABLE_NAME+ "Where ";
		if(nome!=null) {
			selectSQL+=" nome LIKE  = %?%";
		}
		if(generazione!=null) {
			selectSQL+=" generazione = ?";
		}
		if(tipo!=null) {
			selectSQL+=" AND tipo = ?";
		}
		if(prezzo!=null) {
			selectSQL+=" AND prezzo = ?";
		}
		if(nazione!=null) {
			selectSQL+=" AND nazione = ?";
		}

		selectSQL+=" orderby visitato DESC";
		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			
			ResultSet rs = preparedStatement.executeQuery();
			if(nome!=null)
				preparedStatement.setString(1, nome);
			if(generazione!=null)
			preparedStatement.setInt(2, generazione);
			if(tipo!=null)
			preparedStatement.setString(3, tipo.toString());
			if(prezzo!=null) 
			preparedStatement.setFloat(4, prezzo);
			if(nazione!=null)
			preparedStatement.setString(5, nazione);

			while (rs.next()) {
				Prodotto bean=new Prodotto();
				
				bean.setIdProdotto((rs.getInt("idProdotti")));
				bean.setTipo(Tipo.valueOf(rs.getString("tipo")));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setNome(rs.getString("Nome"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setPrezzo(rs.getFloat("Prezzo"));
				bean.setGenerazione(rs.getInt("generazione"));

				bean.setVisitato(rs.getInt("visitato"));
				bean.setNazionalita(rs.getString("Nazionalita"));

				bean.addSprites(new SpritesDAO().doRetrieveByProdotto(bean));
				
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
	public synchronized Collection<Prodotto> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Prodotto> products = new LinkedList<Prodotto>();

		String selectSQL = "SELECT * FROM " + ProdottoDAO.TABLE_NAME ;

		if (order == null || order.equals("")) {
			order="idProdotto";
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, order);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Prodotto bean=new Prodotto();
				
				bean.setIdProdotto((rs.getInt("idProdotti")));
				bean.setTipo(Tipo.valueOf(rs.getString("tipo")));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setNome(rs.getString("Nome"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setPrezzo(rs.getFloat("Prezzo"));
				bean.setGenerazione(rs.getInt("generazione"));

				bean.setVisitato(rs.getInt("visitato"));
				bean.setNazionalita(rs.getString("Nazionalita"));

				bean.addSprites(new SpritesDAO().doRetrieveByProdotto(bean));
				
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
	
	public synchronized Collection<Prodotto> doRetrieveAllClient(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Prodotto> products = new LinkedList<Prodotto>();

		String selectSQL = "SELECT * FROM " + ProdottoDAO.TABLE_NAME + "WHERE Quantita >0 ORDER BY ? DESC Limit 100";

		if (order == null || order.equals("")) {
			order="idProdotto";
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, order);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Prodotto bean=new Prodotto();
				
				bean.setIdProdotto((rs.getInt("idProdotti")));
				bean.setTipo(Tipo.valueOf(rs.getString("tipo")));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setNome(rs.getString("Nome"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setPrezzo(rs.getFloat("Prezzo"));
				bean.setGenerazione(rs.getInt("generazione"));

				bean.setVisitato(rs.getInt("visitato"));
				bean.setNazionalita(rs.getString("Nazionalita"));

				bean.addSprites(new SpritesDAO().doRetrieveByProdotto(bean));
				
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
