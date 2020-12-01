package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class Portal extends Entity{
    public Portal() {
    }

    public Portal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        BombermanGame.mainMap[yUnit][xUnit] = 'b';
    }

    @Override
    public void update() {

    }
}
