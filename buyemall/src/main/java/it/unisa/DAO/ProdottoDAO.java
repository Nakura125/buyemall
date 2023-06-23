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

import it.unisa.bean.Ordine;
import it.unisa.bean.Prodotto;
import it.unisa.bean.Sprites;
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

	private static final String TABLE_NAME = "Prodotti";
	

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
	
	public synchronized Integer doSaveGenerator(Prodotto product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet generatedKeys = null;
		Integer generatedId = null;
		

		String insertSQL = "INSERT INTO " + ProdottoDAO.TABLE_NAME
				+ " (tipo, quantita, nome, descrizione,prezzo,generazione, nazionalita) VALUES (?, ?, ?, ?,?,?,?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, product.getTipo().toString());
			preparedStatement.setInt(2, product.getQuantita());
			preparedStatement.setString(3, product.getNome());
			preparedStatement.setString(4, product.getDescrizione());
			preparedStatement.setFloat(5,product.getPrezzo() );
			preparedStatement.setInt(6,product.getGenerazione() );
			preparedStatement.setString(7,product.getNazionalita() );

			preparedStatement.executeUpdate();

			
	        generatedKeys = preparedStatement.getGeneratedKeys();
	        if (generatedKeys.next()) {
	             generatedId = generatedKeys.getInt(1);
	            // Puoi utilizzare l'id generato per ulteriori operazioni
//	            System.out.println("Id dell'ordine inserito: " + generatedId);
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
	
	
	public synchronized void UpdateQuantita(Integer it,Prodotto pr) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL ="UPDATE prodotti "
				+ "SET Quantita = ? "
				+ "WHERE idProdotti = ? ";
				
				
				try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(insertSQL);
					preparedStatement.setInt(1, it);
					preparedStatement.setInt(2, pr.getIdProdotto());
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
	
	public synchronized void UpdateNome(String n,Prodotto pr) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL ="UPDATE prodotti "
				+ "SET nome = ? "
				+ "WHERE idProdotti = ? ";
				
				
				try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(insertSQL);
					preparedStatement.setString(1, n);
					preparedStatement.setInt(2, pr.getIdProdotto());
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
	
	public synchronized void UpdatePrezzo(Float prezzo,Prodotto pr) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL ="UPDATE prodotti "
				+ "SET prezzo = ? "
				+ "WHERE idProdotti = ? ";
				
				
				try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(insertSQL);
					preparedStatement.setFloat(1, prezzo);
					preparedStatement.setInt(2, pr.getIdProdotto());
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
	
	public synchronized void UpdateDescrizione(String descr,Prodotto pr) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL ="UPDATE prodotti "
				+ "SET descrizione = ? "
				+ "WHERE idProdotti = ? ";
				
				
				try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(insertSQL);
					preparedStatement.setString(1, descr);
					preparedStatement.setInt(2, pr.getIdProdotto());
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
	
	public synchronized void UpdateVisit(Integer it,Prodotto pr) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL ="UPDATE prodotti "
				+ "SET visitato = ? "
				+ "WHERE idProdotti = ? ";
				
				
				try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(insertSQL);
					preparedStatement.setInt(1, it);
					preparedStatement.setInt(2, pr.getIdProdotto());
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
		Connection connection = null;
	    PreparedStatement PreparedStatement = null;


	    String deleteSQL = "DELETE FROM " + ProdottoDAO.TABLE_NAME + " WHERE idProdotti = ?";

	    try {
	        connection = ds.getConnection();
	        PreparedStatement = connection.prepareStatement(deleteSQL);
	        PreparedStatement.setInt(1, code);
	        PreparedStatement.executeUpdate();

	        
	        return true;
	    } finally {
	        try {
	            if (PreparedStatement != null)
	                PreparedStatement.close();
	        } finally {
	            if (connection != null)
	                connection.close();
	        }
	    }
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

				bean.addSprites(Sprites.nullSprites());

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
		int count=1;
		if(generazione ==null&&tipo==null&&prezzo ==null&&nazione==null)
			return doRetrieveAllClient("visitato");

		String selectSQL = "SELECT * FROM " + ProdottoDAO.TABLE_NAME+ " Where ";
		if(nome!=null) {
			selectSQL+=" nome LIKE  ? AND";
		}
		if(generazione!=null) {
			selectSQL+=" generazione = ? AND ";
		}
		if(tipo!=null) {
			selectSQL+=" tipo = ? AND ";
		}
		if(prezzo!=null) {
			selectSQL+="prezzo = ? AND ";
		}
		if(nazione!=null) {
			selectSQL+=" nazione = ? AND";
		}

		selectSQL+="  Quantita > 0 order by visitato DESC limit 100";
		//System.out.println(selectSQL);

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			
			if(nome!=null) {
				nome="%"+nome+"%";
				preparedStatement.setString(count, nome);
				count+=1;
			}
			if(generazione!=null) {
				preparedStatement.setInt(count, generazione);
				count+=1;
			}
			if(tipo!=null)
			{
				preparedStatement.setString(count, tipo.toString());
				count+=1;
			}if(prezzo!=null) 
			{
				preparedStatement.setFloat(count, prezzo);
				count+=1;
			}
			if(nazione!=null)
			{
				preparedStatement.setString(count, nazione);
				count+=1;
			}
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

				bean.addSprites(Sprites.nullSprites());
				
				products.add(bean);
				
				//System.out.println(bean);
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
	
	public synchronized Collection<Prodotto> doRetrieveAllClient(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Prodotto> products = new LinkedList<Prodotto>();

		String selectSQL = "SELECT * FROM " + ProdottoDAO.TABLE_NAME + " WHERE Quantita >0 ORDER BY visitato DESC Limit 100";

		if (order == null || order.equals("")) {
			order="idProdotto";
		}
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			//preparedStatement.setString(1, order);
			
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
	
	
	public synchronized Collection<Prodotto> doRetrieveAllRAND() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Prodotto> products = new LinkedList<Prodotto>();

		String selectSQL = "SELECT * FROM " + ProdottoDAO.TABLE_NAME + " WHERE Quantita >0 ORDER BY RAND() DESC Limit 100";

		
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
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
	
	// query particolari di filtro
	
	public synchronized List<String> selectAllType() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		List<String> products = new LinkedList<String>();

		String selectSQL = "SELECT distinct tipo FROM  "+ProdottoDAO.TABLE_NAME+" where Quantita> 0";

		
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				products.add(rs.getString("tipo"));
				
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
	
	public synchronized List<Integer> selectAllGeneration() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		List<Integer> products = new LinkedList<Integer>();

		String selectSQL = "SELECT distinct generazione FROM "+ProdottoDAO.TABLE_NAME+" where Quantita> 0";

		
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				products.add(rs.getInt("generazione"));
				
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
	
	public synchronized Integer countForFilter(String nome,Integer generazione,Tipo tipo,Float  prezzo,String nazione) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Integer products = null;
		int count=1;
		

		String selectSQL = "SELECT Count(*) as conteggio  FROM " + ProdottoDAO.TABLE_NAME+ " Where ";
		if(nome!=null) {
			selectSQL+=" nome LIKE  ? AND";
		}
		if(generazione!=null) {
			selectSQL+=" generazione = ? AND ";
		}
		if(tipo!=null) {
			selectSQL+=" tipo = ? AND ";
		}
		if(prezzo!=null) {
			selectSQL+="prezzo = ? AND ";
		}
		if(nazione!=null) {
			selectSQL+=" nazione = ? AND";
		}

		selectSQL+="  Quantita > 0";
		//System.out.println(selectSQL);

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			
			if(nome!=null) {
				nome="%"+nome+"%";
				preparedStatement.setString(count, nome);
				count+=1;
			}
			if(generazione!=null) {
				preparedStatement.setInt(count, generazione);
				count+=1;
			}
			if(tipo!=null)
			{
				preparedStatement.setString(count, tipo.toString());
				count+=1;
			}if(prezzo!=null) 
			{
				preparedStatement.setFloat(count, prezzo);
				count+=1;
			}
			if(nazione!=null)
			{
				preparedStatement.setString(count, nazione);
				count+=1;
			}
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				
				
				
				
				products=rs.getInt("conteggio");
				
				//System.out.println(bean);
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
	
	
	public synchronized Collection<Prodotto> RetrieveByFiltersPaged(String nome,Integer generazione,Tipo tipo,Float  prezzo,String nazione,Integer offset,Integer page) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<Prodotto> products = new LinkedList<Prodotto>();
		int count=1;
		

		String selectSQL = "SELECT * FROM " + ProdottoDAO.TABLE_NAME+ " Where ";
		if(nome!=null) {
			selectSQL+=" nome LIKE  ? AND";
		}
		if(generazione!=null) {
			selectSQL+=" generazione = ? AND ";
		}
		if(tipo!=null) {
			selectSQL+=" tipo = ? AND ";
		}
		if(prezzo!=null) {
			selectSQL+="prezzo = ? AND ";
		}
		if(nazione!=null) {
			selectSQL+=" nazione = ? AND";
		}

		selectSQL+="  Quantita > 0 order by visitato DESC LIMIT ? OFFSET ? ";
		//System.out.println(selectSQL);

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			
			if(nome!=null) {
				nome="%"+nome+"%";
				preparedStatement.setString(count, nome);
				count+=1;
			}
			if(generazione!=null) {
				preparedStatement.setInt(count, generazione);
				count+=1;
			}
			if(tipo!=null)
			{
				preparedStatement.setString(count, tipo.toString());
				count+=1;
			}if(prezzo!=null) 
			{
				preparedStatement.setFloat(count, prezzo);
				count+=1;
			}
			if(nazione!=null)
			{
				preparedStatement.setString(count, nazione);
				count+=1;
			}
			
			preparedStatement.setInt(count, offset);

			preparedStatement.setInt(count+1, page);
			
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

				bean.addSprites(Sprites.nullSprites());
				
				products.add(bean);
				
				//System.out.println(bean);
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
