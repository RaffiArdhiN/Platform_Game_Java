//Saya Raffi Ardhi Naufal NIM 2202495 mengerjakan Tugas Masa Depan
// dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan
// kecurangan seperti yang telah dispesifikasikan. Aamiin.
package ViewModel;

import View.GameView;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

//Kelas Input mengatur input dari player berupa keyboard dalam game.
// Kelas ini mengimplementasikan KeyListener untuk mendengarkan aksi keyboard.

public class Input implements KeyListener {
    private GameView view;
    public Input(GameView view) {
        this.view = view;

        view.addKeyListener(this);
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            view.getGame().getPlayer().setyVelocity(-20);
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            view.getGame().getPlayer().setxVelocity(-5);
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            view.getGame().getPlayer().setxVelocity(5);
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            view.getGame().getPlayer().setyVelocity(0);
            view.getGame().getPlayer().setxVelocity(0);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
}