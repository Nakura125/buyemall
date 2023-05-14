package it.unisa.interfaces;

import java.sql.SQLException;
import java.util.Collection;

public interface IBeanDao<T,K> {
	public void doSave(T product) throws SQLException;

	public boolean doDelete(K code) throws SQLException;

	public T doRetrieveByKey(K code) throws SQLException;
	
	public Collection<T> doRetrieveAll(String order) throws SQLException;
}
