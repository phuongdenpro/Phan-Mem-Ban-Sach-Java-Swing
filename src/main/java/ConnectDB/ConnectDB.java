package connectdb;

import java.sql.*;

public class ConnectDB {
    public static Connection conn = null;

    public ConnectDB() throws SQLException {
        String severName = "localhost";
        String databaseName = "HieuSach";
        String username = "sa";
        String password = "sapassword";
        String url = "jdbc:sqlserver://" + severName + ":1433;databaseName=" + databaseName;
        this.conn = DriverManager.getConnection(url, username, password);
        System.out.println(conn);
    }

    public void disconnect() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    
    public String resultSetToString(ResultSet rs) {

        StringBuffer buf = new StringBuffer();
        buf.append("[");
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int nColumns = metaData.getColumnCount();
            for (int i = 1; i <= nColumns; ++i) {
                buf.append(metaData.getColumnName(i));
                buf.append(" = ");
                buf.append(rs.getString(i));
                if (i < nColumns)
                    buf.append(" , ");
            }
        } catch (SQLException e) {
            buf.append(e.getMessage());
            e.printStackTrace();
        }
        buf.append("]");

        return buf.toString();
    }
    
    public void printResultSet(ResultSet rs) {
    	System.out.println(resultSetToString(rs));
    }

    public static void main(String[] args) throws SQLException {
    	ConnectDB connectDB = new ConnectDB();
	}
}
