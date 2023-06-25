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
			System.err.println("Error:" + e.getMessage());
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
	
	public synchronized void doSaveCart(Account product,Prodotto pd) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO carrello"
				+ " (username,idProdotto) VALUES (?,?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getUsername());
			preparedStatement.setInt(2, pd.getIdProdotto());

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
				+ " SET nome = ?, cognome=?, email=? "
				+ "WHERE username = ? ";
				
				
				try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(insertSQL);
					preparedStatement.setString(1, nome);

					preparedStatement.setString(2, cognome);

					preparedStatement.setString(3, email);

					preparedStatement.setString(4, pr.getUsername());
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
	
	public synchronized void UpdatePassword(String password,Account pr) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL ="UPDATE account "
				+ "SET password=? "
				+ "WHERE username = ? ";
				
				
				try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(insertSQL);
					preparedStatement.setString(1, password);

					

					preparedStatement.setString(2, pr.getUsername());
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
	
	public synchronized void UpdateIndirizzo(Indirizzo id,Account pr) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL ="UPDATE account "
				+ "SET idindirizzo=? "
				+ "WHERE username = ? ";
				
				
				try {
					connection = ds.getConnection();
					preparedStatement = connection.prepareStatement(insertSQL);
					preparedStatement.setInt(1, id.getIdIndirizzo());

					

					preparedStatement.setString(2, pr.getUsername());
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
				
				bean.setI(new IndirizzoDAO().doRetrieveByKey(rs.getInt("idindirizzo")));
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
	
	
	public synchronized Account doRetrieveByEmail(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Account bean = new Account();

		String selectSQL = "SELECT * FROM " + AccountDAO.TABLE_NAME + " WHERE email = ?";

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
	public static synchronized List<Prodotto> recoverCart(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		List<Prodotto> products = new LinkedList<>();

		
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
	
	
	
	

	@Override
	public synchronized List<Account> doRetrieveAll(String order) throws SQLException {

		return null;
	}
	
	public synchronized boolean DeleteCart(Account username) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM carrello WHERE  username=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			
			preparedStatement.setString(1, username.getUsername());

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
	
	public synchronized boolean DeleteCart(Prodotto code,Account username) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM carrello WHERE idprodotto = ? AND username=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code.getIdProdotto());
			preparedStatement.setString(2, username.getUsername());

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

}
