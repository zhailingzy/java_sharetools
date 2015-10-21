package ShareTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class SqlOperator {
	public enum dbtype {
		sqlserver, sqlite, mysql, odbc
	};

	private String s_errTitle = "Sql Operator Error";

	private Connection conn;
	private Statement mystatement;
	private String connstr;
	private MessageBox msgBox;
	private boolean showError;

	public SqlOperator(dbtype dbtype, String connecturl, boolean showError) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		msgBox = new MessageBox();
		this.showError = showError;
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
			if (showError)
				msgBox.showMessage(s_errTitle, e.getMessage(), JOptionPane.ERROR_MESSAGE);
			throw e;
		}
		openConnection();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		try {
			super.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (showError)
				msgBox.showMessage(s_errTitle, e.getMessage(), JOptionPane.ERROR_MESSAGE);
			throw e;
		}
		disConnect();
	}

	public void openConnection() throws SQLException  {
		disConnect();
		try {
			conn = DriverManager.getConnection(connstr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (showError)
				msgBox.showMessage(s_errTitle, e.getMessage(), JOptionPane.ERROR_MESSAGE);
			throw e;
		}

		try {
			mystatement = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (showError)
				msgBox.showMessage(s_errTitle, e.getMessage(), JOptionPane.ERROR_MESSAGE);
			throw e;
		}

	}

	public void disConnect() throws SQLException {
		if (mystatement != null)
			try {
				mystatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				if (conn != null)
					conn.close();// close anyway....
				e.printStackTrace();
				if (showError)
					msgBox.showMessage(s_errTitle, e.getMessage(), JOptionPane.ERROR_MESSAGE);
				throw e;
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if (showError)
					msgBox.showMessage(s_errTitle, e.getMessage(), JOptionPane.ERROR_MESSAGE);
				throw e;
			}
	}

	public ResultSet executeQuery(String sql) throws SQLException  {

		try {
			return mystatement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (showError)
				msgBox.showMessage(s_errTitle, e.getMessage(), JOptionPane.ERROR_MESSAGE);
			throw e;
		}
	}

	public int executeUpdate(String sql) throws SQLException  {
		try {
			return mystatement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (showError)
				msgBox.showMessage(s_errTitle, e.getMessage(), JOptionPane.ERROR_MESSAGE);
			throw e;
		}
	}

	public int getMaxRows() throws SQLException  {
		try {
			return mystatement.getMaxRows();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (showError)
				msgBox.showMessage(s_errTitle, e.getMessage(), JOptionPane.ERROR_MESSAGE);
			throw e;
		}
	}

	public void setMaxRows(int max) throws SQLException  {
		try {
			mystatement.setMaxRows(max);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (showError)
				msgBox.showMessage(s_errTitle, e.getMessage(), JOptionPane.ERROR_MESSAGE);
			throw e;
		}
	}

}
