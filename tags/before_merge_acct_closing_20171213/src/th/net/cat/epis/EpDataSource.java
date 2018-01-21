package th.net.cat.epis;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

public class EpDataSource extends OracleDataSource {

	@SuppressWarnings("deprecation")
	public EpDataSource() throws SQLException {
		super();

		java.util.Properties cacheProps = new java.util.Properties();
		cacheProps.setProperty("MinLimit", "5"); // the cache size is 5 at least
		cacheProps.setProperty("MaxLimit", "25");
		cacheProps.setProperty("InitialLimit", "3"); // create 3 connections at startup
		cacheProps.setProperty("InactivityTimeout", "1800"); // seconds
		cacheProps.setProperty("AbandonedConnectionTimeout", "900"); // seconds
		cacheProps.setProperty("MaxStatementsLimit", "10");
		cacheProps.setProperty("PropertyCheckInterval", "60"); // seconds

		setConnectionCacheProperties(cacheProps);
		setConnectionCachingEnabled(true);
	}

	private static final long serialVersionUID = 1425471491696723058L;

	@Override
	public Connection getConnection(String userID, String pass) throws SQLException {
		Connection connection = super.getConnection(userID, pass);
		connection.setAutoCommit(false);
		return connection;
	}
}