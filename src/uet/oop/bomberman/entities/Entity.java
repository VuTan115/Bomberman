package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    public Entity() {
    }

    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x, x1, x2, x3;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y, y1, y2, y3, y4;

    long time30 = 0, time500 = 0;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    protected Image img;

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public boolean checkCollision(int nextStepX, int nextStepY) {
        return true;
    }

    public void fixedUpdate30 () {

    }

    public void fixedUpadte500() {

    }

    public void update() {
        long time = System.currentTimeMillis();

        if (time30 != time / 30) {
            time30 = time / 30;
            fixedUpdate30();
        }
        if (time500 != time / 500) {
            time500 = time / 500;
            fixedUpadte500();
        }
    }
}
