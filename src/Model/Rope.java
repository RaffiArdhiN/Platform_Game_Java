//Saya Raffi Ardhi Naufal NIM 2202495 mengerjakan Tugas Masa Depan
// dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan
// kecurangan seperti yang telah dispesifikasikan. Aamiin.
package Model;

import java.awt.*;

//Kelas Rope merepresentasikan gantungan atas di dalam game.
// Ini mencakup atribut posisi, ukuran, kecepatan, serta status tali
// seperti sudah diinjak atau tidak.

public class Rope {
    private int x, y;
    private int width, height;
    private boolean hasBeenSteppedOn;
    private int points;
    private int xVelocity;
    private boolean showPoints;
    private Image image;

    public Rope(int x, int y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hasBeenSteppedOn = false;
        this.points = 60 - this.height / 10;//this.height / 10; // Menentukan poin berdasarkan tinggi building
        this.showPoints = false;
        this.image = image;
        this.xVelocity = -7;
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setHasBeenSteppedOn(boolean hasBeenSteppedOn) {
        this.hasBeenSteppedOn = hasBeenSteppedOn;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public void setShowPoints(boolean showPoints) {
        this.showPoints = showPoints;
    }
    public int getHeight() {
        return height;
    }
    public boolean hasBeenSteppedOn() {
        return hasBeenSteppedOn;
    }
    public void setSteppedOn(boolean steppedOn) {
        this.hasBeenSteppedOn = steppedOn;
        this.showPoints = true;
    }
    public boolean shouldShowPoints() {
        return showPoints;
    }
    public int getPoints() {
        return points;
    }
    public Image getImage() { return image; }
    public void setImage(Image image) { this.image = image; }
    public int getxVelocity() {
        return xVelocity;
    }
    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

}
