package uet.oop.bomberman.entities.mobile;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Balloon extends Entity {
    // 0:up   1:down   2:left   3: right;
    private int direction = 3;
    Sprite[][] sprites = new Sprite[4][3];
    private int way = 4;
    private int currentColm = 0;
    Random random = new Random();

    public Balloon(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        x1 = x + 17 * 2;
        y1 = y;
        x2 = x;
        y2 = y + 25 * 2;
        x3 = x1;
        y3 = y2;
        sprites[0][0] = Sprite.balloom_up1;
        sprites[0][1] = Sprite.balloom_up2;
        sprites[0][2] = Sprite.balloom_up3;
        sprites[1][0] = Sprite.balloom_down1;
        sprites[1][1] = Sprite.balloom_down2;
        sprites[1][2] = Sprite.balloom_down3;
        sprites[2][0] = Sprite.balloom_left1;
        sprites[2][1] = Sprite.balloom_left2;
        sprites[2][2] = Sprite.balloom_left3;
        sprites[3][0] = Sprite.balloom_right1;
        sprites[3][1] = Sprite.balloom_right2;
        sprites[3][2] = Sprite.balloom_right3;
    }

    int toU(int k) {
        return Math.round(k / Sprite.SCALED_SIZE);
    }

    @Override
    public boolean checkCollision(int nextStepX, int nextStepY) {
        return BombermanGame.mainMap[nextStepX][nextStepY] == '#'
                || BombermanGame.mainMap[nextStepX][nextStepY] == '*';
    }

    private void doRandom() {
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
    }

    @Override
    public void fixedUpdate30() {
        BombermanGame.mainMap[toU((y + y2) / 2)][toU((x + x1) / 2)] = ' ';
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
        BombermanGame.mainMap[toU((y + y2) / 2)][toU((x + x1) / 2)] = '1';
    }

    @Override
    public void fixedUpadte500() {
        doRandom();
    }
}
