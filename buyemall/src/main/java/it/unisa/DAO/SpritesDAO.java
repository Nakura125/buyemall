package it.unisa.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.bean.Prodotto;
import it.unisa.bean.Sprites;
import it.unisa.interfaces.IBeanDao;

public class SpritesDAO implements IBeanDao<Sprites,Integer>{
	private static final Logger LOGGER = Logger.getLogger(SpritesDAO.class.getName());

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

	private static final String TABLE_NAME = "Sprites";
	
	
	@Override
	public synchronized void doSave(Sprites product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + SpritesDAO.TABLE_NAME
				+ " (link,link_small,idprodotto) VALUES (?,?,?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, product.getLink());
			preparedStatement.setString(2, product.getLink_small());

			preparedStatement.setInt(3, product.getProdotto().getIdProdotto());

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
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + SpritesDAO.TABLE_NAME + " WHERE CODE = ?";

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
	public synchronized Sprites doRetrieveByKey(Integer code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Sprites bean = new Sprites();

		String selectSQL = "SELECT * FROM " + SpritesDAO.TABLE_NAME + " WHERE idsprites = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();
			
						
			while (rs.next()) {
				bean.setIdSprites(rs.getInt("idSprites"));
				bean.setLink(rs.getString("link"));
				bean.setLink_small(rs.getString("link_small"));
				bean.setProdotto(new ProdottoDAO().doRetrieveByKey((rs.getInt("idProdotto"))));
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
	
	public synchronized List<Sprites> doRetrieveByProdotto(Prodotto pd) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		List<Sprites> products = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + SpritesDAO.TABLE_NAME + " WHERE idprodotto=?";

		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, pd.getIdProdotto());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Sprites bean = new Sprites();

				bean.setIdSprites(rs.getInt("idSprites"));
				bean.setLink(rs.getString("link"));
				bean.setLink_small(rs.getString("link_small"));
				
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
	public synchronized Collection<Sprites> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Sprites> products = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + SpritesDAO.TABLE_NAME + " ORDER BY ? Limit 100";

		if (order == null || order.equals("")) {
			order="idsprites";
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, order);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Sprites bean = new Sprites();

				bean.setIdSprites(rs.getInt("idSprites"));
				bean.setLink(rs.getString("link"));
				bean.setLink(rs.getString("link_small"));
				bean.setProdotto(new ProdottoDAO().doRetrieveByKey((rs.getInt("idProdotto"))));
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