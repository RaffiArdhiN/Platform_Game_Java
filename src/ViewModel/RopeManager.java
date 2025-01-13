//Saya Raffi Ardhi Naufal NIM 2202495 mengerjakan Tugas Masa Depan
// dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan
// kecurangan seperti yang telah dispesifikasikan. Aamiin.
package ViewModel;

import Model.Rope;
import View.GameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

//Kelas RopeManager mengelola pembuatan dan penjadwalan gantungan atas dalam permainan.

public class RopeManager {
    private GameView view;
    private ArrayList<Rope> ropes;
    private Random rand;
    private Timer ropeTimer;
    private boolean isFirstSpawn = true;

    public RopeManager(GameView view) {
        rand = new Random();
        this.view = view;
        ropes = new ArrayList<>();
        ropeTimer = new Timer(1900, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spawnRope();
            }
        });
        ropeTimer.setInitialDelay(0);
        ropeTimer.start();
    }

    //Metode untuk membuat dan menambahkan rope baru ke dalam daftar rope.
    public void spawnRope() {
        int x;
        if (isFirstSpawn) {
            isFirstSpawn = false; // Setelah spawn pertama kali, set variabel ini ke false
            x = rand.nextInt(100) + 460;
        }
        else {
            x = rand.nextInt(200) + 800;
        }
        int y = 0;
        int height = rand.nextInt(100) + 250;
        ropes.add(new Rope(x, y, 50, height, view.getRopeImage()));
    }
    public ArrayList<Rope> getRopes() {return ropes;}
    public Timer getRopeTimer() {return ropeTimer;}
}
