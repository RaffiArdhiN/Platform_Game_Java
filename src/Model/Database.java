//Saya Raffi Ardhi Naufal NIM 2202495 mengerjakan Tugas Masa Depan
// dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan
// kecurangan seperti yang telah dispesifikasikan. Aamiin.
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

//Kelas Database digunakan untuk melakukan koneksi dan interaksi
// dengan database MySQL. Ini mencakup pembuatan koneksi, eksekusi query,
// dan menutup koneksi.

public class Database {
    private String ConAddress = "jdbc:mysql://localhost:3306/db_score?user=root&password=";
    private Statement stmt = null; // koneksi query
    private ResultSet rs = null; // hasil query
    private Connection conn = null; // koneksi MySQL dan basis data
//.getDeclaredConstructor()
    public Database () throws Exception, SQLException {
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(ConAddress);
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_score","root","");
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        }
        catch (SQLException es) {
            throw es;
        }
    }

    public void createQuery (String Query) throws Exception, SQLException {
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(Query);
            if (stmt.execute(Query)) {
                rs = stmt.getResultSet();
            }
        }
        catch(SQLException es) {
            throw es;
        }
    }

    public void createUpdate(String Query) throws Exception, SQLException {
        try {
            stmt = conn.createStatement();
            int hasil = stmt.executeUpdate(Query);
        } catch (SQLException es) {
            throw es;
        }
    }

    public ResultSet getResult() throws Exception {
        ResultSet Temp = null;
        try {
            return rs;
        }
        catch (Exception ex) {
            return Temp;
        }
    }

    public void closeResult() throws SQLException, Exception {
        if(rs != null) {
            try {
                rs.close();
            }
            catch (SQLException sqlEx) {
                rs = null;
                throw sqlEx;
            }
        }
        if(stmt != null) {
            try {
                stmt.close();
            }
            catch (SQLException sqlEx) {
                stmt = null;
                throw sqlEx;
            }
        }
    }

    public void closeConnection() throws SQLException, Exception {
        if(conn != null) {
            try {
                conn.close();
            }
            catch (SQLException sqlEx) {
                conn = null;
            }
        }
    }
}
