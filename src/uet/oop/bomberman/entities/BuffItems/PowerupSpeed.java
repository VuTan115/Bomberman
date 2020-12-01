package uet.oop.bomberman.entities.BuffItems;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;

public class PowerupSpeed extends Entity {
    private boolean bombed = false;

    public PowerupSpeed() {
    }

    public PowerupSpeed(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        BombermanGame.mainMap[yUnit][xUnit] = 'b';
    }

    @Override
    public void update() {

    }
}
