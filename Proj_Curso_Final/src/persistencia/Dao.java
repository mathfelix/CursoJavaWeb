package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao {

	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USUARIO = "system";
	private static final String SENHA = "1234";
	
	protected Connection conn;
	protected PreparedStatement stmt;
	protected ResultSet rs;
	
	protected void abrirConexao() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(URL, USUARIO, SENHA);
	}
	
	protected void fecharConexao() throws Exception {
		if (conn != null) {
			conn.close();
		}
	}
	
}
