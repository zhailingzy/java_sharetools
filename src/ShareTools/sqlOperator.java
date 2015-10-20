package ShareTools;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlOperator {
	public enum dbtype {
		sqlserver, sqlite, mysql, odbc
	};

	private Connection conn;
	private Statement mystatement;
	private String connstr;

	public sqlOperator(dbtype dbtype, String connecturl) {
		// TODO Auto-generated constructor stub
		String classname;
		connstr = "jdbc".concat(":").concat(dbtype.toString()).concat(":").concat(connecturl);
		switch (dbtype) {
		case sqlserver:
			classname = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			break;
		case sqlite:
			classname = "org.sqlite.JDBC";
			break;
		case mysql:
			classname = "com.mysql.jdbc.Driver";
		case odbc:
			classname = "sun.jdbc.odbc.JdbcOdbcDriver";
		default:
			classname = "sun.jdbc.odbc.JdbcOdbcDriver";
			break;
		}
		try {
			Class.forName(classname);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		openConnection();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		disConnect();
	}

	public void openConnection() {
		try {
			disConnect();
			conn = DriverManager.getConnection(connstr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mystatement = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void disConnect() {
		if (mystatement != null)
			try {
				mystatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	public ResultSet executeQuery(String sql) throws SQLException {

		return mystatement.executeQuery(sql);
	}

	public int executeUpdate(String sql) throws SQLException {
		return mystatement.executeUpdate(sql);
	}

	public void close() throws SQLException {
		mystatement.close();
	}

	public int getMaxRows() throws SQLException {
		return mystatement.getMaxRows();
	}

	public void setMaxRows(int max) throws SQLException {
		mystatement.setMaxRows(max);
	}

	public boolean isClosed() throws SQLException {
		return mystatement.isClosed();
	}

}
