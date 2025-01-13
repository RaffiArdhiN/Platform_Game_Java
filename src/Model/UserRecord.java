//Saya Raffi Ardhi Naufal NIM 2202495 mengerjakan Tugas Masa Depan
// dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan
// kecurangan seperti yang telah dispesifikasikan. Aamiin.
package Model;

//Kelas UserRecord merepresentasikan data player yang mencakup id, username,
// score, up (jumlah kali naik), dan down (jumlah kali turun).

public class UserRecord {
    private String id;
    private String username;
    private int score;
    private int up;
    private int down;

    public UserRecord() {}
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getUp() {
        return up;
    }
    public void setUp(int up) {
        this.up = up;
    }
    public int getDown() {
        return down;
    }
    public void setDown(int down) {
        this.down = down;
    }
}
