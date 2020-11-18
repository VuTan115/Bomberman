package uet.oop.bomberman.entities.mobile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Entity {
    private double speed;
    public int spaceStep = 4;
    public static Sprite prevSprite = null;
    static private int countdown = 0;

    private List<Bom> bombs = new ArrayList<>();

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setCountdown() {
        countdown = 0;
    }

    public void moveUp() {
        if (countdown == 0) {
            countdown = 3;
            if (Sprite.player_up == prevSprite) {
                prevSprite = Sprite.player_up_1;
                System.out.println(1);
            } else if (Sprite.player_up_1 == prevSprite) {
                prevSprite = Sprite.player_up_2;
                System.out.println(2);
            } else {
                prevSprite = Sprite.player_up;
                System.out.println(3);
            }
        }
        countdown--;

        if (!checkCollision(x / Sprite.SCALED_SIZE, (y - spaceStep) / Sprite.SCALED_SIZE)
                && !checkCollision((x - 1) / Sprite.SCALED_SIZE + 1, (y - spaceStep) / Sprite.SCALED_SIZE)) {
            y -= (int) this.speed + spaceStep;
        }
    }

    public void moveDown() {
        if (countdown == 0) {
            countdown = 3;
            if (Sprite.player_down == prevSprite) {
                prevSprite = Sprite.player_down_1;
                System.out.println(1);
            } else if (Sprite.player_down_1 == prevSprite) {
                prevSprite = Sprite.player_down_2;
                System.out.println(2);
            } else {
                prevSprite = Sprite.player_down;
                System.out.println(3);
            }
        }
        countdown--;

        if (!checkCollision(x / Sprite.SCALED_SIZE, (y + Sprite.SCALED_SIZE + spaceStep - 1) / Sprite.SCALED_SIZE)
                && !checkCollision((x - 1) / Sprite.SCALED_SIZE + 1, (y + Sprite.SCALED_SIZE + spaceStep - 1) / Sprite.SCALED_SIZE)) {
            y += (int) this.speed + spaceStep;
        }
    }

    public void moveRight() {
        if (countdown == 0) {
            countdown = 3;
            if (Sprite.player_right == prevSprite) {
                prevSprite = Sprite.player_right_1;
                System.out.println(1);
            } else if (Sprite.player_right_1 == prevSprite) {
                prevSprite = Sprite.player_right_2;
                System.out.println(2);
            } else {
                prevSprite = Sprite.player_right;
                System.out.println(3);
            }
        }
        countdown--;

        if (!checkCollision((x + Sprite.SCALED_SIZE + spaceStep - 1) / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE)
                && !checkCollision((x + Sprite.SCALED_SIZE + spaceStep - 1) / Sprite.SCALED_SIZE, (y - 1) / Sprite.SCALED_SIZE + 1)) {
            x += (int) this.speed + spaceStep;
        }
    }

    public void moveLeft() {
        if (countdown == 0) {
            countdown = 3;
            if (Sprite.player_left == prevSprite) {
                prevSprite = Sprite.player_left_1;
                System.out.println(1);
            } else if (Sprite.player_left_1 == prevSprite) {
                prevSprite = Sprite.player_left_2;
                System.out.println(2);
            } else {
                prevSprite = Sprite.player_left;
                System.out.println(3);
            }
        }
        countdown--;

        if (!checkCollision((x - spaceStep) / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE)
                && !checkCollision((x - spaceStep) / Sprite.SCALED_SIZE, (y - 1) / Sprite.SCALED_SIZE + 1)) {
            x -= (int) this.speed + spaceStep;
        }
    }

    public void undou(KeyEvent ke, Bomber bomber) {
        switch (ke.getCode()) {
            case UP:
            case W:
                bomber.moveUp();
                bomber.setImg(Bomber.prevSprite.getFxImage());
                break;

            case DOWN:
            case S:
                bomber.moveDown();
                bomber.setImg(Bomber.prevSprite.getFxImage());
                break;

            case LEFT:
            case A:
                bomber.moveLeft();
                bomber.setImg(Bomber.prevSprite.getFxImage());
                break;

            case RIGHT:
            case D:
                bomber.moveRight();
                bomber.setImg(Bomber.prevSprite.getFxImage());
                break;

            case SPACE:
            case SHIFT:
                Bom bom = new Bom(bomber.getX() / Sprite.SCALED_SIZE, bomber.getY() / Sprite.SCALED_SIZE, Sprite.bomb.getFxImage());
                bombs.add(bom);
                BombermanGame.mainMap[bomber.getY() / Sprite.SCALED_SIZE][bomber.getX() / Sprite.SCALED_SIZE] = 'b';
                System.out.println("create bomb");
                break;
        }
    }

    public boolean checkCollision(int nextStepX, int nextStepY) {
        System.out.println(nextStepX + " " + nextStepY);
        char temp = BombermanGame.mainMap[nextStepY][nextStepX];
        if (temp == '#' || temp == '*' || temp == 'b') {
            return true;
        }
        return false;
    }

    @Override
    public void update() {
        bombs.forEach(Entity::update);
    }

    @Override
    public void render(GraphicsContext gc) {
        bombs.forEach(g -> g.render(gc));
        super.render(gc);
    }
}
