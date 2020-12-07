package uet.oop.bomberman.entities.BuffItems;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;

public class SpeedItem extends Entity {
    private boolean bombed = false;

    public SpeedItem() {
    }

    public SpeedItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        BombermanGame.mainMap[yUnit][xUnit] = 's';
    }

    @Override
    public void update() {

    }
}
