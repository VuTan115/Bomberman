package uet.oop.bomberman.entities.mobile;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private double speed;

    public Bomber() {
    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void moveUp() {
        
        if (!checkCollision(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE - 1)) {
            y -= (int)this.speed + Sprite.SCALED_SIZE;
        }
    }

    public void moveLeft() {
        if (!checkCollision(x / Sprite.SCALED_SIZE - 1, y / Sprite.SCALED_SIZE)) {
            x -= (int)this.speed + Sprite.SCALED_SIZE;
        }
    }

    public void moveDown() {
        if (!checkCollision(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE + 1)) {
            y += (int)this.speed + Sprite.SCALED_SIZE;
        }
    }

    public void moveRight() {
        if (!checkCollision(x / Sprite.SCALED_SIZE + 1, y / Sprite.SCALED_SIZE)) {
            x += (int)this.speed + Sprite.SCALED_SIZE;
        }
    }

    public boolean checkCollision(int nextStepX, int nextStepY) {

        char temp = BombermanGame.mainMap[nextStepY][nextStepX];
        if (temp == '#' || temp == '*' || temp == 'b') {
            return true;
        }
        return false;
    }

    @Override
    public void update() {

    }
}
