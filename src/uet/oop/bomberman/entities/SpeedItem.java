package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class SpeedItem extends Entity {
    private boolean bombed = false;

    public SpeedItem() {
    }

    public SpeedItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        BombermanGame.mainMap[yUnit][xUnit] = 'b';
    }

    @Override
    public void update() {

    }
}
