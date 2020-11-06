package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    public Bomber() {
    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    public void moveUp() {
        if (!checkCollision(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE - 1)) {
            y = y - Sprite.SCALED_SIZE;
        }
    }

    public void moveLeft() {
        if (!checkCollision(x / Sprite.SCALED_SIZE - 1, y / Sprite.SCALED_SIZE)) {
            x = x - Sprite.SCALED_SIZE;
        }
    }

    public void moveDown() {
        if (!checkCollision(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE + 1)) {
            y = y + Sprite.SCALED_SIZE;
        }
    }

    public void moveRight() {
        if (!checkCollision(x / Sprite.SCALED_SIZE + 1, y / Sprite.SCALED_SIZE)) {
            x = x + Sprite.SCALED_SIZE;
        }
    }

    public boolean checkCollision(int nextStepX, int nextStepY) {

        char temp = BombermanGame.mainMap[nextStepY][nextStepX];
        if (temp == '#' || temp == '*') {
            return true;
        }
        return false;
    }

    @Override
    public void update() {

    }
}
