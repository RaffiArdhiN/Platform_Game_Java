//Saya Raffi Ardhi Naufal NIM 2202495 mengerjakan Tugas Masa Depan
// dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan
// kecurangan seperti yang telah dispesifikasikan. Aamiin.
package ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Model.TabelScore;
import Model.UserRecord;

//Kelas ProsesUserRecord bertanggung jawab untuk memproses data
// dari TabelScore dan menyediakan akses data terstruktur dalam
// bentuk ArrayList<UserRecord>.

public class ProsesUserRecord {
    private String error;
    private TabelScore tabelScore;
    private ArrayList<UserRecord> data;

    public ProsesUserRecord() {
        try {
            tabelScore = new TabelScore();
            data = new ArrayList<>(); // Inisialisasi data di sini

        } catch (Exception e) {
            error = e.toString();
        }
    }
    public void ProsesDataUserRecord() {
        try {
            if (tabelScore == null) {
                throw new IllegalStateException("TabelScore is null. Check initialization.");
            }
            List<Map<String, Object>> scores = tabelScore.getAllScores();
            for (Map<String, Object> scoreData : scores) {
                UserRecord user = new UserRecord();
                user.setUsername((String) scoreData.get("username"));
                user.setScore((Integer) scoreData.get("score"));
                user.setUp((Integer) scoreData.get("up"));
                user.setDown((Integer) scoreData.get("down"));
                data.add(user);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void addOrUpdateUserScore(String username, int score, int upCount, int downCount) {
        try {
            tabelScore.addOrUpdateUserScore(username, score, upCount, downCount);
        } catch (Exception e) {
            throw new RuntimeException("Error adding or updating user score: " + e.getMessage());
        }
    }
    public String getId(int i) {
        return data.get(i).getId();
    }
    public String getUsername(int i) {
        return data.get(i).getUsername();
    }
    public int getScore(int i) {
        return data.get(i).getScore();
    }
    public int getUp(int i) {
        return data.get(i).getUp();
    }
    public int getDown(int i) {
        return data.get(i).getDown();
    }
    public int getSize() {
        return data.size();
    }
    public List<UserRecord> getData() {
        return data;
    }
}
