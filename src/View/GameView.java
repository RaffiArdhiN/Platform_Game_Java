//Saya Raffi Ardhi Naufal NIM 2202495 mengerjakan Tugas Masa Depan
// dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan
// kecurangan seperti yang telah dispesifikasikan. Aamiin.
package View;

import ViewModel.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameView extends JPanel {
    private Game game; // Objek untuk mengatur logika game
    private BuildingManager buildingmanager; // Objek untuk mengatur manajemen building
    private RopeManager ropemanager; // Objek untuk mengatur manajemen rope
    private ProsesUserRecord proses; // Objek untuk memproses data pengguna
    private Input input; // Objek untuk mengatur input permainan
    private Audio audio; // Objek untuk mengatur audio
    private Image backgroundImage; // Gambar background
    private Image playerImage; // Gambar player
    private Image buildingImage; // Gambar building
    private Image ropeImage; // Gambar rope
    private String username; // Nama pengguna

    // Mengambil gambar
    public GameView(MenuView menu) {
        this.setPreferredSize(new Dimension(800, 600));// Ukuran panel game
        setFocusable(true);

        backgroundImage = new ImageIcon(getClass().getResource("../Assets/download.jpg")).getImage();
        playerImage = new ImageIcon(getClass().getResource("../Assets/george.png")).getImage();
        buildingImage = new ImageIcon(getClass().getResource("../Assets/pohon.png")).getImage();
        ropeImage = new ImageIcon(getClass().getResource("../Assets/tali.png")).getImage();

        // Inisialisasi objek-objek viewmodel
        game = new Game(this, menu);
        buildingmanager = new BuildingManager(this);
        ropemanager = new RopeManager(this);
        input = new Input(this);
        audio = new Audio();

        // Memainkan musik
        audio.playMusic("Assets/PokemonRubySapphireEmeraldRoute101.wav");
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.draw(g);
    }

    public String getUsername(String username) {
        return username;
    }
    public Game getGame() { return game; }
    public BuildingManager getBuildingManager() { return buildingmanager; }
    public RopeManager getRopeManager() { return ropemanager; }

    public void refresh() {
        repaint();
    }

    public Image getRopeImage() { return ropeImage; }

    public Image getBackgroundImage() { return backgroundImage; }

    public Image getPlayerImage() { return playerImage; }

    public Image getBuildingImage() { return buildingImage; }
    public void setUsername(String username) {
        this.username = username;
        game.setUsername(username); // Mengatur username di objek game
    }
}
