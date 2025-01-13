//Saya Raffi Ardhi Naufal NIM 2202495 mengerjakan Tugas Masa Depan
// dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan
// kecurangan seperti yang telah dispesifikasikan. Aamiin.

import View.GameView;
import View.MenuView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Membuat frame utama untuk menu
        JFrame frame = new JFrame("Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Membuat instance MenuView
        MenuView menuView = new MenuView();
        frame.setLocationRelativeTo(null);
        frame.add(menuView);
        frame.setVisible(true);

        // Menambahkan listener untuk property "play" pada menuView
        menuView.addPropertyChangeListener("play", evt -> {
            if ((boolean) evt.getNewValue()) { // Jika property "play" berubah menjadi true
                try {
                    // Membuat frame baru untuk game
                    JFrame gameFrame = new JFrame("Game");
                    gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    gameFrame.setSize(800, 600);
                    gameFrame.setLocationRelativeTo(null);
                    gameFrame.setResizable(false);

                    // Membuat instance GameView dan menambahkannya ke frame game
                    GameView game = new GameView(menuView);
                    gameFrame.add(game);
                    gameFrame.pack();
                    game.requestFocus();
                    gameFrame.setVisible(true);

                    // Menutup frame menu
                    frame.dispose();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Failed to start game: " + e.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        });
    }
}
