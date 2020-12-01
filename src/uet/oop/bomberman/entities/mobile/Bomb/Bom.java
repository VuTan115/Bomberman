package uet.oop.bomberman.entities.mobile.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Player.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

public class Bom extends Entity {
    Sprite prevSprite = Sprite.bomb;

    public Bom() {
    }

    public Bom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.setImg(Sprite.bomb.getFxImage());
    }
    public void bombAnimation(){
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
    public void explosion(){
        this.setImg(null);
        BombermanGame.mainMap[Bomber.bombs.get(0).getY() / Sprite.SCALED_SIZE][Bomber.bombs.get(0).getX() / Sprite.SCALED_SIZE] = ' ';
        Bomber.bombs = new ArrayList<>();
        Bomber.bombLimited = false;
        Bomber.count = 0;
    }
    public void animate(){
        Bomber.count++;
        if (Bomber.count > 3) {
//            img = null;
//            BombermanGame.mainMap[Bomber.bombs.get(0).getY() / Sprite.SCALED_SIZE][Bomber.bombs.get(0).getX() / Sprite.SCALED_SIZE] = ' ';
//            Bomber.bombs = new ArrayList<>();
//            Bomber.bombLimited = false;
//            Bomber.count = 0;
            explosion();
        } else {

//            if (prevSprite == Sprite.bomb) {
//                prevSprite = Sprite.bomb_1;
//            } else if (prevSprite == Sprite.bomb_1) {
//                img = Sprite.bomb_2.getFxImage();
//                prevSprite = Sprite.bomb_2;
//            } else {
//                prevSprite = Sprite.bomb;
//            }
//            img = prevSprite.getFxImage();
            bombAnimation();
        }
    }
    @Override
    public void update() {
       animate();
    }
}
