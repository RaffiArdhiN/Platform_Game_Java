//Saya Raffi Ardhi Naufal NIM 2202495 mengerjakan Tugas Masa Depan
// dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan
// kecurangan seperti yang telah dispesifikasikan. Aamiin.
package View;

import ViewModel.Audio;
import ViewModel.Game;
import ViewModel.ProsesUserRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;

public class MenuView extends JPanel {
    private Audio audio; // Objek untuk mengatur audio
    private ProsesUserRecord prosesUserRecord; // Objek untuk memproses data user record
    private JTextField usernameField; // Field untuk input username
    private JTable scoreTable; // Tabel untuk menampilkan tabel score dari database
    private JButton playButton; // Tombol untuk memulai game
    private JButton quitButton; // Tombol untuk keluar dari game
    private JButton upButton; // Tombol untuk scroll tabel ke atas
    private JButton downButton; // Tombol untuk scroll tabel ke bawah
    private PropertyChangeSupport support;

    private int startIndex; // Indeks awal untuk menampilkan tabel

    public MenuView() {
        audio = new Audio(); // Inisialisasi objek audio
        prosesUserRecord = new ProsesUserRecord(); // Inisialisasi objek proses user record
        support = new PropertyChangeSupport(this);

        setLayout(new BorderLayout());

        // Panel untuk input username
        JPanel usernamePanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                Color color1 = new Color(49, 120, 55); // Hijau gelap
                Color color2 = new Color(138, 109, 59); // Cokelat tua
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };

        JLabel upDownLabel = new JLabel("UP DOWN");
        upDownLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usernamePanel.add(upDownLabel, BorderLayout.NORTH);

        // Panel untuk input username
        JPanel usernameInputPanel = new JPanel();
        usernameInputPanel.setOpaque(false);
        usernameInputPanel.add(new JLabel("Username:"));
        usernameField = new JTextField(15);
        usernameInputPanel.add(usernameField);
        usernamePanel.add(usernameInputPanel, BorderLayout.CENTER);

        scoreTable = new JTable(); // Tabel untuk menampilkan skor
        JScrollPane scrollPane = new JScrollPane(scoreTable); // Tambahkan tabel ke dalam JScrollPane
        scrollPane.setPreferredSize(new Dimension(400, 300)); // Ukuran preferensi scroll pane
        updateScoreTable();

        // Panel untuk menempatkan tombol Play dan Quit
        JPanel buttonPanel = new JPanel();
        playButton = new JButton("Play");
        playButton.addActionListener(e -> {
            String username = usernameField.getText();
            if (username != null && !username.isEmpty()) {
                try {
                    // Menambah atau mengupdate skor player
                    prosesUserRecord.addOrUpdateUserScore(username, 0, 0, 0);
                    audio.stopMusic();
                    support.firePropertyChange("play", false, true);
                    System.out.println(username);
                    support.firePropertyChange("username", null, username); // Memicu perubahan username
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(MenuView.this, "Error saving/updating score: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Tombol untuk keluar dari aplikasi
        quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(playButton);
        buttonPanel.add(quitButton);

        // Tombol untuk scroll tabel ke atas dan ke bawah
        upButton = new JButton("\u25B2"); // Simbol segitiga ke atas Unicode
        upButton.addActionListener(e -> scrollTableUp());
        downButton = new JButton("\u25BC"); // Simbol segitiga ke bawah Unicode
        downButton.addActionListener(e -> scrollTableDown());

        JPanel scrollButtonPanel = new JPanel();
        scrollButtonPanel.setLayout(new BoxLayout(scrollButtonPanel, BoxLayout.Y_AXIS));
        scrollButtonPanel.add(upButton);
        scrollButtonPanel.add(downButton);

        add(usernamePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(scrollButtonPanel, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);

        // Memainkan musik di menu
        audio.playMusic("Assets/PokemonRubySapphireEmeraldLittlerootTown.wav");
    }

    // Memperbarui tabel skor dengan data terbaru
    private void updateScoreTable() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Username", "Score", "Up Count", "Down Count"}, 0);
        prosesUserRecord.ProsesDataUserRecord();
        int rowCount = Math.min(prosesUserRecord.getSize(), 10); // Hanya ambil 10 baris atau kurang
        for (int i = startIndex; i < startIndex + rowCount; i++) {
            if (i >= prosesUserRecord.getSize()) break; // Jika melebihi jumlah data, hentikan
            model.addRow(new Object[]{
                    prosesUserRecord.getUsername(i),
                    prosesUserRecord.getScore(i),
                    prosesUserRecord.getUp(i),
                    prosesUserRecord.getDown(i)
            });
        }
        scoreTable.setModel(model);
    }
    private void scrollTableUp() {
        if (startIndex > 0) {
            startIndex--;
            updateScoreTable();
        }
    }
    private void scrollTableDown() {
        if (startIndex + 10 < prosesUserRecord.getSize()) {
            startIndex++;
            updateScoreTable();
        }
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public Audio getAudio() {
        return audio;
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(propertyName, listener);
    }
}
