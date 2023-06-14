package crud.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	private boolean useLocal = true;
	public DataSource dataSource;

	public ConnectionFactory() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

		if (useLocal) {
			comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/api2sem");
			comboPooledDataSource.setUser("root");
			comboPooledDataSource.setPassword("root");
		} else {
			comboPooledDataSource.setJdbcUrl("jdbc:mysql://34.95.173.113:3306/api2sem");
			comboPooledDataSource.setUser("root");
			comboPooledDataSource.setPassword("@yY{=s9ELktt'vXd");
		}

		this.dataSource = comboPooledDataSource;
	}

	public Connection recuperarConexao() {
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}