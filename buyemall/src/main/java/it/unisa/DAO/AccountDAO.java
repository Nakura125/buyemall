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

import it.unisa.bean.Account;
import it.unisa.bean.Indirizzo;
import it.unisa.bean.Prodotto;
import it.unisa.bean.Tipo;
import it.unisa.interfaces.IBeanDao;

public class AccountDAO implements IBeanDao<Account,String>{
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

	private static final String TABLE_NAME = "Account";
	

	@Override
	public synchronized void doSave(Account product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + AccountDAO.TABLE_NAME
				+ " (username,nome, cognome, email, password,idindirizzo) VALUES (?,?, ?, ?, ?,?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getUsername());
			preparedStatement.setString(2, product.getNome());
			preparedStatement.setString(3, product.getCognome());
			preparedStatement.setString(4, product.getEmail());
			preparedStatement.setString(5, product.getPassword());
			preparedStatement.setInt(6, product.getI().getIdIndirizzo());

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

	public synchronized Account Login(String username,String password) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Account bean = new Account();

		String selectSQL = "SELECT * FROM " + AccountDAO.TABLE_NAME + " WHERE username = ? AND password= ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);

			preparedStatement.setString(2, password);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setUsername((rs.getString("username")));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("Password"));
				bean.setActive(rs.getBoolean("attivo"));

				bean.setI(new IndirizzoDAO().doRetrieveByKey(rs.getInt("idIndirizzo")));

				bean.addPag(new MetodiPagamentoDAO().doRetrieveAByUsername(bean.getUsername()));

				bean.addCart(AccountDAO.recoverCart(bean.getUsername()));
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
	
	public synchronized Account LoginEmail(String email,String password) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Account bean = new Account();

		String selectSQL = "SELECT * FROM " + AccountDAO.TABLE_NAME + " WHERE email = ? AND password= ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);

			preparedStatement.setString(2, password);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setUsername((rs.getString("username")));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("Password"));
				bean.setActive(rs.getBoolean("attivo"));

				bean.addPag(new MetodiPagamentoDAO().doRetrieveAByUsername(bean.getUsername()));

				bean.addCart(AccountDAO.recoverCart(bean.getUsername()));
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
	
	
	public synchronized void UpdateInfo(String nome,String cognome,String email,Account pr) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL ="UPDATE account"
				+ "SET nome = ?, cognome=?,email=? "
				+ "WHERE username = ? ";
				
				
				try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(insertSQL);
					preparedStatement.setString(1, nome);

					preparedStatement.setString(2, cognome);

					preparedStatement.setString(3, email);

					preparedStatement.setString(4, pr.getUsername());
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
	
	public synchronized void UpdatePassword(String password,Account pr) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL ="UPDATE account"
				+ "SET password=? "
				+ "WHERE username = ? ";
				
				
				try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(insertSQL);
					preparedStatement.setString(1, password);

					

					preparedStatement.setString(2, pr.getUsername());
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
	
	public synchronized void UpdateIndirizzo(Indirizzo id,Account pr) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL ="UPDATE account"
				+ "SET idindirizzo=? "
				+ "WHERE username = ? ";
				
				
				try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(insertSQL);
					preparedStatement.setInt(1, id.getIdIndirizzo());

					

					preparedStatement.setString(2, pr.getUsername());
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
	
	public synchronized void UpdateActive(Boolean id,Account pr) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL ="UPDATE account"
				+ "SET active=? "
				+ "WHERE username = ? ";
				
				
				try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(insertSQL);
					preparedStatement.setBoolean(1, id);

					

					preparedStatement.setString(2, pr.getUsername());
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
	public synchronized boolean doDelete(String code) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public synchronized Account doRetrieveByKey(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Account bean = new Account();

		String selectSQL = "SELECT * FROM " + AccountDAO.TABLE_NAME + " WHERE username = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setUsername((rs.getString("username")));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("Password"));
				bean.setActive(rs.getBoolean("attivo"));

				bean.addPag(new MetodiPagamentoDAO().doRetrieveAByUsername(bean.getUsername()));

				bean.addCart(AccountDAO.recoverCart(bean.getUsername()));
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
	
	private synchronized static Collection<Prodotto> recoverCart(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Prodotto> products = new LinkedList<>();

		//TODO
		String selectSQL = "select idProdotti,tipo,P.nome,quantita,descrizione,prezzo,Nazionalita,Generazione from carrello as C, `account` as AC,prodotti as P\r\n"
				+ "where ac.username=c.username and p.idProdotti=c.idprodotto  and AC.username= ?;";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Prodotto bean=new Prodotto();
				
				bean.setIdProdotto((rs.getInt("idProdotti")));
				bean.setTipo(Tipo.valueOf(rs.getString("tipo")));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setNome(rs.getString("Nome"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setPrezzo(rs.getFloat("Prezzo"));
				bean.setNazionalita(rs.getString("Nazionalita"));
				bean.setGenerazione(rs.getInt("generazione"));
				
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
	public synchronized Collection<Account> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
