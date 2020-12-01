package uet.oop.bomberman.entities.mobile.Nomster;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon  extends Entity {
    // 0:up   1:down   2:left   3: right;
    private int direction = 3;
    Sprite[][] sprites = new Sprite[4][3];
    public Balloon() {
    }

    public Balloon(int xUnit, int yUnit) {
        sprites[0][0] = Sprite.balloom_up1;
        sprites[0][1] = Sprite.balloom_up2;
/*        sprites[0][2] = Sprite.balloom_up3;
        sprites[1][0] = Sprite.balloom_down1;
        sprites[1][1] = Sprite.balloom_down2;
        sprites[1][2] = Sprite.balloom_down3;
        sprites[2][0] = Sprite.balloom_left1;
        sprites[2][1] = Sprite.balloom_left2;
        sprites[2][2] = Sprite.balloom_left3;*/
        sprites[3][0] = Sprite.balloom_right1;
        sprites[3][1] = Sprite.balloom_right2;
        sprites[3][2] = Sprite.balloom_right3;
    }

    public Balloon(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void fixedUpdate30() {

    }

    @Override
    public void update() {

    }
}
