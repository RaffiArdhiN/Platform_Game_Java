//Saya Raffi Ardhi Naufal NIM 2202495 mengerjakan Tugas Masa Depan
// dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan
// kecurangan seperti yang telah dispesifikasikan. Aamiin.
package ViewModel;

import Model.Building;
import Model.Player;
import Model.Rope;
import View.GameView;
import View.MenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.*;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

//Kelas Game mengontrol logika utama dari game.

public class Game implements ActionListener {
    private int playerX = 100, playerY = 300; // posisi awal pemain
    private int playerWidth = 40, playerHeight = 40; // ukuran pemain
    private ProsesUserRecord prosesUserRecord; // objek untuk memproses rekaman pengguna
    private ArrayList<Building> buildings; // daftar bangunan
    private ArrayList<Rope> ropes; // daftar tali
    private Player player; // objek pemain
    private Timer timer; // timer untuk mengatur pergerakan dan update permainan
    private int score; // skor permainan
    private int upCount; // hitungan naik
    private int downCount; // hitungan turun
    private boolean gameOver; // status permainan apakah sudah berakhir atau belum
    private Random rand; // objek untuk menghasilkan angka acak
    private Clip clip; // objek untuk memutar audio
    private GameView view; // tampilan permainan
    private MenuView menu; // tampilan menu
    private String username; // nama pengguna

    public Game(GameView view, MenuView menu) {
        this.view = view;
        this.menu = menu;
        this.prosesUserRecord = new ProsesUserRecord();
        player = new Player(playerX, playerY, playerWidth, playerHeight,  view.getPlayerImage());
        score = 0;
        upCount = 0;
        downCount = 0;
        gameOver = false;
        rand = new Random();
        buildings = new ArrayList<>();
        ropes = new ArrayList<>();


        timer = new Timer(16, this);
        timer.start();
    }

    public Player getPlayer() { return player; }

    public Timer getTimer() {
        return timer;
    }

    //Metode untuk menggambar seluruh elemen game
    public void draw(Graphics g) {
        // Menggambar latar belakang game
        g.drawImage(view.getBackgroundImage(), 0, 0, 800, 600, null);

        // Menggambar player ke layar
        g.drawImage(view.getPlayerImage(), player.getX(), player.getY(), player.getWidth(), player.getHeight(), null);

        // Menggambar setiap building ke layar
        for (int i = 0; i < view.getBuildingManager().getBuildings().size(); i++) {
            Building building = view.getBuildingManager().getBuildings().get(i);
            g.drawImage(building.getImage(), building.getX(), building.getY(), building.getWidth(), building.getHeight(), null);

            // Menampilkan poin dari building di atasnya
            int scorej = building.getPoints();
            g.setColor(Color.BLACK);
            g.drawString("" + building.getPoints(), building.getX() + building.getWidth() / 2, building.getY() - 10);

        }
        // Menggambar setiap rope ke layar
        for (int j = 0; j < view.getRopeManager().getRopes().size(); j++) {
            Rope rope = view.getRopeManager().getRopes().get(j);
            g.drawImage(rope.getImage(), rope.getX(), rope.getY(), rope.getWidth(), rope.getHeight(), null);

            // Menampilkan poin dari rope di atasnya
            int scorej = rope.getPoints();
            g.setColor(Color.BLACK);
//            System.out.println(scorej);
            g.drawString("" + rope.getPoints(), rope.getX() + rope.getWidth() / 2, rope.getY() + rope.getHeight() + 20);

        }

        int score = getScore();
        int upCount = getUpCount();
        int downCount = getDownCount();
        boolean gameOver = isGameOver();

        // Menampilkan skor, hitungan up, dan hitungan down di layar
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 20);
        g.drawString("Up: " + upCount, 10, 40);
        g.drawString("Down: " + downCount, 10, 60);

        // Jika game berakhir, menampilkan pesan "Game Over" di tengah layar
        if (gameOver) {
            g.setColor(Color.RED);
            g.drawString("Game Over", 800 / 2 - 30, 600 / 2);
        }
    }

    //Metode untuk menggerakkan player, building dan rope di setiap iterasi timer.
    public void move() {
        // Mengatur pergerakan player ke bawah dengan gravitasi
        player.setyVelocity(player.getyVelocity() + 1);
        // Mengatur pergerakan horizontal player
        player.setY(player.getY() + player.getyVelocity());
        player.setX(player.getX() + player.getxVelocity());
        // Memastikan pemain tetap dalam batas layar
        player.setY(Math.max(player.getY(), 0));
        player.setX(Math.min(player.getX(), 800 - player.getWidth()));

        // Mengatur pergerakan horizontal setiap building
        for (int i = 0; i < view.getBuildingManager().getBuildings().size(); i++) {
            Building building = view.getBuildingManager().getBuildings().get(i);
            building.setX(building.getX() + building.getxVelocity());
        }
        // Mengatur pergerakan horizontal setiap rope
        for (int i = 0; i < view.getRopeManager().getRopes().size(); i++) {
            Rope rope = view.getRopeManager().getRopes().get(i);
            rope.setX(rope.getX() + rope.getxVelocity());
        }

        // Jika player jatuh ke luar batas bawah layar, game berakhir
        if (player.getY() > 600) {
            player.setY(600);
            gameOver = true;
        }
    }

    //Metode yang dipanggil setiap kali timer menghasilkan ActionEvent.
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        view.repaint();
        if(gameOver) {
            // Jika permainan berakhir, menghentikan timer dan timer-timer lainnya
            timer.stop();
            view.getBuildingManager().getBuildingTimer().stop();
            view.getRopeManager().getRopeTimer().stop();
            try {
                // Menambahkan atau memperbarui skor pengguna ke dalam database
                prosesUserRecord.addOrUpdateUserScore(menu.getUsername(), score, upCount, downCount);
            }
            catch (Exception ex) {
                throw new RuntimeException("Error adding or updating user score: " + ex.getMessage());
            }
        }
        else {
            // Jika game belum berakhir, menghapus objek yang
            // keluar dari layar dan memeriksa tabrakan antara
            // pemain dengan bangunan dan tali
            removeOffScreenObjects();
            checkCollisions();
        }
    }

    //Metode untuk menghapus building dan rope yang keluar dari layar.
    private void removeOffScreenObjects() {
        Iterator<Building> buildingIterator = buildings.iterator();
        while (buildingIterator.hasNext()) {
            Building building = buildingIterator.next();
            if (building.getX() + building.getWidth() < 0) {
                buildingIterator.remove();
            }
        }
        Iterator<Rope> ropeIterator = ropes.iterator();
        while (ropeIterator.hasNext()) {
            Rope rope = ropeIterator.next();
            if (rope.getX() + rope.getWidth() < 0) {
                ropeIterator.remove();
            }
        }
    }

    private void checkCollisions() {
        // Deteksi collision antara player dengan building dan rope
        for (Building building : view.getBuildingManager().getBuildings()) {
//            player.checkPlayerOnBuilding(building, 10);
            if (player.getBounds().intersects(building.getBounds())) {
//            if (player.isOnBuilding()) {
                player.setOnBuilding(true);
                player.landOnBuilding(building);
                if (!building.hasBeenSteppedOn()) {
                    score += building.getPoints();
                    downCount++;
                    building.setHasBeenSteppedOn(true);
                }
            }
            else {
                player.setOnBuilding(false);
            }
        }
        for (Rope rope : view.getRopeManager().getRopes()) {
            if (player.getBounds().intersects(rope.getBounds())) {
                player.setOnRope(true);
                player.grabRope(rope);
                if (!rope.hasBeenSteppedOn()&&rope.getX()-player.getX()<20) {
                    player.setY(rope.getY() + rope.getHeight()/2);
                    player.setyVelocity(0);
                    score += rope.getPoints();
                    upCount++;
                    player.setOnGround(true);
                    rope.setHasBeenSteppedOn(true);
                }
                player.setOnBuilding(false);
                player.setOnRope(true);
            }
        }
    }
    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public ArrayList<Rope> getRopes() {
        return ropes;
    }

    public int getScore() {
        return score;
    }

    public int getUpCount() {
        return upCount;
    }

    public int getDownCount() {
        return downCount;
    }

    public boolean isGameOver() {
        return gameOver;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}