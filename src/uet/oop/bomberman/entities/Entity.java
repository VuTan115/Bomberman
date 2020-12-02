package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public abstract class Entity {
    public Entity() {
    }

    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x, x1, x2, x3;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y, y1, y2, y3, y4;

    protected int countdown = 0;
    protected int direction = 3;
    protected int way = 4;

    Random random = new Random();

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
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    protected boolean inTheArea(Entity entity) {
        int a1 = entity.getX();
        int a2 = entity.getY();
        int b1 = a1 + Sprite.SCALED_SIZE;
        int b2 = a2 + Sprite.SCALED_SIZE;
        return (a1 <= x && x <= b1
                && a2 <= y && y <= b2)
                || (a1 <= x1 && x1 <= b1
                && a2 <= y1 && y1 <= b2)
                || (a1 <= x2 && x2 <= b1
                && a2 <= y2 && y2 <= b2)
                || (a1 <= x3 && x3 <= b1
                && a2 <= y3 && y3 <= b2);
    }

    public boolean checkCollision(int nextStepX, int nextStepY) {
        return BombermanGame.mainMap[nextStepX][nextStepY] == '#'
                || BombermanGame.mainMap[nextStepX][nextStepY] == '*'
                || BombermanGame.mainMap[nextStepX][nextStepY] == 'b';
    }

    protected void doRandom() {
        int rdm = random.nextInt(4);
        while (true){
            if (rdm == 0 && !checkCollision(toU(y - way), toU(x))
                    && !checkCollision(toU(y - way), toU(x1))) break;
            else if (rdm == 1 && !checkCollision(toU(y2 + way), toU(x2))
                    && !checkCollision(toU(y2 + way), toU(x3))) break;
            else if (rdm == 2 && !checkCollision(toU(y), toU(x - way))
                    && !checkCollision(toU(y2), toU(x - way))) break;
            else if (rdm == 3 && !checkCollision(toU(y), toU(x1 + way))
                    && !checkCollision(toU(y2), toU(x1 + way))) break;
            rdm = random.nextInt(4);
        }
        if (direction != rdm) {
            direction = rdm;
        }
    }

    public int toU(int k) {
        return Math.round(k / Sprite.SCALED_SIZE);
    }

    public void fixedUpdate30() {
    }

    public void fixedUpdate500() {
        doRandom();
    }

    public void update() {
        long time = System.currentTimeMillis();

        if (time30 != time / 30) {
            time30 = time / 30;
            fixedUpdate30();
        }
        if (time500 != time / 500) {
            time500 = time / 500;
            fixedUpdate500();
        }
    }
}
