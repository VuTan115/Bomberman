package uet.oop.bomberman.entities.mobile;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Entity {

    Sprite[][] sprites = new Sprite[4][3];
    private int currentColm = 0;
    private char prevType = '\0';

    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        x1 = x + 17 * 2;
        y1 = y;
        x2 = x;
        y2 = y + 25 * 2;
        x3 = x1;
        y3 = y2;
        sprites[0][0] = Sprite.oneal_up1;
        sprites[0][1] = Sprite.oneal_up2;
        sprites[0][2] = Sprite.oneal_up3;
        sprites[1][0] = Sprite.oneal_down1;
        sprites[1][1] = Sprite.oneal_down2;
        sprites[1][2] = Sprite.oneal_down3;
        sprites[2][0] = Sprite.oneal_left1;
        sprites[2][1] = Sprite.oneal_left2;
        sprites[2][2] = Sprite.oneal_left3;
        sprites[3][0] = Sprite.oneal_right1;
        sprites[3][1] = Sprite.oneal_right2;
        sprites[3][2] = Sprite.oneal_right3;
    }

    @Override
    public boolean checkCollision(int nextStepX, int nextStepY) {
        return nextStepX <= 1 || nextStepX >= 12 || nextStepY >= 29 || nextStepY <= 1;
    }

    @Override
    protected void doRandom() {
        int rdm = random.nextInt(4);
        while (true){
            if (rdm == 0 && !checkCollision(toU(y - way), toU(x))
                    && !checkCollision(toU(y - way), toU(x1))) break;
            else if (rdm == 1 && !checkCollision(toU(y2 + way), toU(x2))
                    && !checkCollision(toU(y2 + way), toU(x3))) break;
            else if (rdm == 2 && !checkCollision(toU(y), toU(x - way))
                    && !checkCollision(toU(y2), toU(x - way))) break;
            else if (rdm == 3 && !checkCollision(toU(y), toU(x1 + way))
                    && !checkCollision(toU(y2), toU(x1 + way))) break;
            rdm = random.nextInt(4);
        }
        if (direction != rdm) {
            direction = rdm;
        }
        img = sprites[direction][currentColm].getFxImage();
    }

    @Override
    public void fixedUpdate30() {
        if (!dead) {
            if (prevType == '\0' || prevType == '1' || prevType == '2')
                BombermanGame.mainMap[toU((y + y2) / 2)][toU((x + x1) / 2)] = ' ';
            else BombermanGame.mainMap[toU((y + y2) / 2)][toU((x + x1) / 2)] = prevType;
            switch (direction) {
                case 0:
                    if (checkCollision(toU(y - way), toU(x))
                            || checkCollision(toU(y - way), toU(x1))) {
                        doRandom();
                        fixedUpdate30();
                        break;
                    }
                    y -= way;
                    y1 = y;
                    y2 -= way;
                    y3 = y2;
                    break;
                case 1:
                    if (checkCollision(toU(y2 + way), toU(x2))
                            || checkCollision(toU(y2 + way), toU(x3))) {
                        doRandom();
                        fixedUpdate30();
                        break;
                    }
                    y += way;
                    y1 = y;
                    y2 += way;
                    y3 = y2;
                    break;
                case 2:
                    if (checkCollision(toU(y), toU(x - way))
                            || checkCollision(toU(y2), toU(x - way))) {
                        doRandom();
                        fixedUpdate30();
                        break;
                    }
                    x -= way;
                    x1 -= way;
                    x2 = x;
                    x3 = x1;
                    break;
                case 3:
                    if (checkCollision(toU(y), toU(x1 + way))
                            || checkCollision(toU(y2), toU(x1 + way))) {
                        doRandom();
                        fixedUpdate30();
                        break;
                    }
                    x += way;
                    x1 += way;
                    x2 = x;
                    x3 = x1;
                    break;
            }
            if (countdown == 0) {
                if (currentColm == 2) currentColm = 1;
                else currentColm++;
                img = sprites[direction][currentColm].getFxImage();
                countdown = 3;
            } else countdown--;
            prevType = BombermanGame.mainMap[toU((y + y2) / 2)][toU((x + x1) / 2)];
            BombermanGame.mainMap[toU((y + y2) / 2)][toU((x + x1) / 2)] = '2';
        }
    }

    @Override
    public void fixedUpdate500() {
        if (!dead) doRandom();
    }
}
