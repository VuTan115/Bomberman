package uet.oop.bomberman.entities.mobile;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bom extends Entity {
    Sprite prevSprite = Sprite.bomb;

    public Bom() {
    }

    public Bom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.setImg(Sprite.bomb.getFxImage());
    }

    @Override
    public void update() {
        if (prevSprite == Sprite.bomb) {
            prevSprite = Sprite.bomb_1;
        } else if (prevSprite == Sprite.bomb_1) {
            img = Sprite.bomb_2.getFxImage();
            prevSprite = Sprite.bomb_2;
        } else {
            prevSprite = Sprite.bomb;
        }
        img = prevSprite.getFxImage();
    }
}
