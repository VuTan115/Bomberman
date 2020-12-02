package uet.oop.bomberman.entities.mobile;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Doll extends Entity {
    // 0:up   1:down   2:left   3: right;
    private int diretion = 3;
   // Sprite[][] sprites = new Sprite[4][2];
    private Bomber bomber;
    private int speed = 1;
    private Sprite sprite = null;
    public Doll() {

    }

    public Doll (int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
//        sprites[0][0] = Sprite.balloom_up1;
//        sprites[0][1] = Sprite.balloom_up2;
//        sprites[1][0] = Sprite.balloom_down1;
//        sprites[1][1] = Sprite.balloom_down2;
//        sprites[2][0] = Sprite.balloom_left1;
//        sprites[2][1] = Sprite.balloom_left2;
//        sprites[3][0] = Sprite.balloom_right1;
//        sprites[3][1] = Sprite.balloom_right2;
    }

    public void setBomber(Bomber bomber) {
        this.bomber = bomber;
    }

    /*public void undou() {
        int xUnit = this.getX() / Sprite.SCALED_SIZE;
        int yUnit = this.getY() / Sprite.SCALED_SIZE;
        if (bomber.getX() - xUnit < 0) {
            if (BombermanGame.mainMap[xUnit-1][yUnit] != '*' || BombermanGame.mainMap[xUnit - 1][yUnit] != '#') {
                sprite = Sprite.doll_left1;
                this.setImg(sprite.getFxImage());
                if (sprite == Sprite.doll_left1) {
                    sprite = Sprite.doll_left2;
                    this.setImg(sprite.getFxImage());
                }
                xUnit--;
            }
        } else if (bomber.getY() - yUnit < 0) {
            if (BombermanGame.mainMap[xUnit][yUnit - 1] != '*' || BombermanGame.mainMap[xUnit][yUnit - 1] != '#') {
                sprite = Sprite.doll_down1;
                this.setImg(sprite.getFxImage());
                if (sprite == Sprite.doll_down1) {
                    sprite = Sprite.doll_down2;
                    this.setImg(sprite.getFxImage());
                }
                yUnit--;
            }
        } else if (bomber.getX() - xUnit > 0) {
            if (BombermanGame.mainMap[xUnit + 1][yUnit] != '*' || BombermanGame.mainMap[xUnit + 1][yUnit] != '#') {
                sprite = Sprite.doll_right1;
                this.setImg(sprite.getFxImage());
                if (sprite == Sprite.doll_right1) {
                    sprite = Sprite.doll_right2;
                    this.setImg(sprite.getFxImage());
                }
                xUnit++;
            }
        } else if (bomber.getY() - yUnit < 0) {
            if (BombermanGame.mainMap[xUnit][yUnit + 1] != '*' || BombermanGame.mainMap[xUnit][yUnit + 1] != '#') {
                sprite = Sprite.doll_up1;
                this.setImg(sprite.getFxImage());
                if (sprite == Sprite.doll_up1) {
                    sprite = Sprite.doll_up2;
                    this.setImg(sprite.getFxImage());
                }
                yUnit++;
            }
        }
    }
    @Override
    public void fixedUpdate30() {
        undou();
    }

     */
}
