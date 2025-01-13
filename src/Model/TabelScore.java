//Saya Raffi Ardhi Naufal NIM 2202495 mengerjakan Tugas Masa Depan
// dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan
// kecurangan seperti yang telah dispesifikasikan. Aamiin.
package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Kelas TabelScore mengelola akses dan manipulasi data skor dari tabel tscore
// dalam database. Melakukan operasi seperti mengambil semua skor,
// menambahkan atau memperbarui skor pengguna, dan
// memeriksa keberadaan pengguna dalam basis data.

public class TabelScore extends Database {
    public TabelScore() throws Exception, SQLException {
        super();
    }
    public List<Map<String, Object>> getAllScores() {
        List<Map<String, Object>> scores = new ArrayList<>();
        String query = "SELECT username, score, up, down FROM tscore";
        try {
            createQuery(query);
            ResultSet resultSet = getResult();
            while (resultSet.next()) {
                Map<String, Object> scoreData = new HashMap<>();
                scoreData.put("username", resultSet.getString("username"));
                scoreData.put("score", resultSet.getInt("score"));
                scoreData.put("up", resultSet.getInt("up"));
                scoreData.put("down", resultSet.getInt("down"));
                scores.add(scoreData);
            }
            return scores;
        } catch (Exception e) {
            System.out.println(e.toString());
            return scores;
        }
    }
    public void addOrUpdateUserScore(String username, int score, int upCount, int downCount) throws SQLException, Exception {
        if (isUserExists(username)) {
            updateUserScore(username, score, upCount, downCount);
        } else {
            addNewUser(username, score, upCount, downCount);
        }
    }

    private boolean isUserExists(String username) throws SQLException, Exception {
        String query = "SELECT COUNT(*) AS count FROM tscore WHERE username = '" + username + "'";
        createQuery(query);
        try (ResultSet resultSet = getResult()) {
            resultSet.next();
            boolean exists = resultSet.getInt("count") > 0;
            closeResult();
            return exists;
        }
    }
    private void addNewUser(String username, int score, int upCount, int downCount) throws SQLException, Exception {
        String query = "INSERT INTO tscore (username, score, up, down) VALUES ('" + username + "', " + score + ", " + upCount + ", " + downCount + ")";
        createUpdate(query);
    }
    private void updateUserScore(String username, int score, int upCount, int downCount) throws SQLException, Exception {
        String query = "UPDATE tscore SET score = " + score + ", up = " + upCount + ", down = " + downCount + " WHERE username = '" + username + "'";
        createUpdate(query);
    }
}
