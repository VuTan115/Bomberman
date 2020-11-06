package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    public Bomber() {
    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    public void moveUp() {
        y = y - Sprite.SCALED_SIZE;
    }

    public void moveLeft() {
        x = x - Sprite.SCALED_SIZE;
    }

    public void moveDown() {
        y = y + Sprite.SCALED_SIZE;
    }

    public void moveRight() {
        x = x + Sprite.SCALED_SIZE;
    }

    @Override
    public void update() {

    }
}
