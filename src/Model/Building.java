//Saya Raffi Ardhi Naufal NIM 2202495 mengerjakan Tugas Masa Depan
// dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan
// kecurangan seperti yang telah dispesifikasikan. Aamiin.
package Model;

import java.awt.*;

//Kelas Building merepresentasikan sebuah pijakan bawah dalam permainan.
// Setiap bangunan memiliki posisi (x, y), lebar, tinggi, gambar, dan beberapa
// atribut tambahan.

public class Building {
    private int x, y;
    private int width, height;
    private boolean hasBeenSteppedOn;
    private int points;
    private boolean showPoints;
    private int xVelocity;
    private Image image;

    public void setHasBeenSteppedOn(boolean hasBeenSteppedOn) {
        this.hasBeenSteppedOn = hasBeenSteppedOn;
    }

    public Building(int x, int y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.image = image;
        this.height = height;
        this.hasBeenSteppedOn = false;
        this.points = this.height / 10; // Menentukan poin berdasarkan tinggi building
        this.showPoints = false;
        this.xVelocity = -9;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean hasBeenSteppedOn() {
        return hasBeenSteppedOn;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setSteppedOn(boolean steppedOn) {
        this.hasBeenSteppedOn = steppedOn;
        this.showPoints = true;
    }

    public int getPoints() {
        return points;
    }

    public boolean shouldShowPoints() {
        return showPoints;
    }
}
