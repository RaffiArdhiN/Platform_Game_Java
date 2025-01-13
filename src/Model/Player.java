//Saya Raffi Ardhi Naufal NIM 2202495 mengerjakan Tugas Masa Depan
// dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan
// kecurangan seperti yang telah dispesifikasikan. Aamiin.
package Model;

import java.awt.*;

//Kelas Player merepresentasikan karakter player dalam game.
// Ini mencakup atribut posisi, ukuran, kecepatan, serta status
// pemain seperti berada di tanah, bangunan, atau tali.


public class Player {
    private Image image;
    private int x, y;
    private int width, height;
    private int yVelocity, xVelocity;
    private boolean onGround;
    private boolean onBuilding; // new attribute to track if player is on a building
    private boolean onRope; // new attribute to track if player is on a rope
    private String username; // Tambahkan atribut username

    public boolean isOnGround() {
        return onGround;
    }
    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }
    public boolean isOnBuilding() {
        return onBuilding;
    }
    public void setOnBuilding(boolean onBuilding) {
        this.onBuilding = onBuilding;
    }
    public boolean isOnRope() {
        return onRope;
    }
    public void setOnRope(boolean onRope) {
        this.onRope = onRope;
    }

    public Player(int x, int y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.onGround = false;
        this.onBuilding = false;
        this.onRope = false;
        this.image = image;
        this.yVelocity = -6;
        this.xVelocity = 0;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public void landOnBuilding(Building building) {
        y = building.getY() - height;
        yVelocity = 0;
//        onGround = true;
        onBuilding = true;
        onRope = false; // Reset the rope state
    }
    public void grabRope(Rope rope) {
        System.out.println(rope.getHeight());
//        x = rope.getX() + rope.getWidth() / 2 - width / 2; // Set player x ke tengah tali
        onRope = true;
//        onGround = true;
        onBuilding = false; // Reset the building state
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
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    public int getxVelocity() {
        return xVelocity;
    }
    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }
    public int getyVelocity() {
        return yVelocity;
    }
    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
}
