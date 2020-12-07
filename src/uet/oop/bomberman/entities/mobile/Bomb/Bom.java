package uet.oop.bomberman.entities.mobile.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Player.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

public class Bom extends Entity {
    Sprite prevSprite = Sprite.bomb;
    private int bomLength = 1;
    public static Sprite[] flame;
    int countTime;

    public Bom() {
    }

    public Bom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.setImg(Sprite.bomb.getFxImage());
    }

    public void bombAnimation() {
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

    //    public void flameAnimation(){
//        if (prevSprite == Sprite.bomb) {
//            prevSprite = Sprite.bomb_1;
//        } else if (prevSprite == Sprite.bomb_1) {
//            img = Sprite.bomb_2.getFxImage();
//            prevSprite = Sprite.bomb_2;
//        } else {
//            prevSprite = Sprite.bomb;
//        }
//        img = prevSprite.getFxImage();
//    }
    public void checkDirection(int currentX, int currentY) {

    }

    public void explosion() {
        //delete bomb
        int currentPosX = Bomber.bombs.get(0).getX() / Sprite.SCALED_SIZE;
        int currentPosY = Bomber.bombs.get(0).getY() / Sprite.SCALED_SIZE;
        BombermanGame.mainMap[currentPosY][currentPosX] = 'f';
//        Bomber.bombs=new ArrayList<>();
        Bomber.bombs.get(0).setImg(Sprite.bomb_exploded.getFxImage());
        Bomber.bombLimited = false;
        Bomber.count = 0;
        // create flame
        if (BombermanGame.mainMap[currentPosY][currentPosX - 1] != '#') {
            BombermanGame.flame.add(new Flame(currentPosX, currentPosY - 1, Sprite.explosion_vertical.getFxImage()));
//            BombermanGame.mainMap[currentPosY][currentPosX - 1] = ' ';
//            if (BombermanGame.mainMap[currentPosY][currentPosX - 1] == '*') {
//                for (Entity e : BombermanGame.entities) {
//                    if (e.getY()/Sprite.SCALED_SIZE == currentPosY && e.getX()/Sprite.SCALED_SIZE == currentPosX - 1) {
//                        e.setImg(Sprite.brick_exploded.getFxImage());
//                    }
//                }
//            }
        }
        if (BombermanGame.mainMap[currentPosY][currentPosX + 1] != '#') {
            BombermanGame.flame.add(new Flame(currentPosX, currentPosY + 1, Sprite.explosion_vertical.getFxImage()));

        }
        if (BombermanGame.mainMap[currentPosY + 1][currentPosX] != '#') {
            BombermanGame.flame.add(new Flame(currentPosX +1, currentPosY, Sprite.explosion_horizontal.getFxImage()));

        }
        if (BombermanGame.mainMap[currentPosY - 1][currentPosX] != '#') {
            BombermanGame.flame.add(new Flame(currentPosX - 1, currentPosY, Sprite.explosion_horizontal.getFxImage()));

        }
    }

    public void counter() {
        countTime++;
        System.out.println(countTime);
    }

    public void animate() {
        Bomber.count++;
        //System.out.println(Bomber.count++);
        if (Bomber.count > 3) {
            explosion();
        } else {
            bombAnimation();
        }
    }

    @Override
    public void update() {
        animate();

    }
}
