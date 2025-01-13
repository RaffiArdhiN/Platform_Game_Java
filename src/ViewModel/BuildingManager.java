//Saya Raffi Ardhi Naufal NIM 2202495 mengerjakan Tugas Masa Depan
// dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan
// kecurangan seperti yang telah dispesifikasikan. Aamiin.
package ViewModel;

import Model.Building;
import Model.Player;
import View.GameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

//Kelas BuildingManager mengelola pembuatan dan penjadwalan pijakan bawah dalam permainan.

public class BuildingManager {
    private GameView view;
    private ArrayList<Building> buildings;
    private Random rand;
    private Timer buildingTimer;
    private boolean isFirstSpawn = true;

    public BuildingManager(GameView view) {
        rand = new Random();
        this.view = view;
        buildings = new ArrayList<>();

        buildingTimer = new Timer(1300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spawnBuilding();
            }
        });
        buildingTimer.setInitialDelay(0);
        buildingTimer.start();
    }

    //Metode untuk membuat dan menambahkan building baru ke dalam daftar building.
    public void spawnBuilding() {
        int x;
        if (isFirstSpawn) {
            isFirstSpawn = false;
            x = rand.nextInt(20) + 300;
        }
        else {
            x = rand.nextInt(200) + 700;
        }

        int height = rand.nextInt(200) + 100;
        int y = 600 - height;
        buildings.add(new Building(x, y, 100, height, view.getBuildingImage()));
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public Timer getBuildingTimer() {
        return buildingTimer;
    }
}
