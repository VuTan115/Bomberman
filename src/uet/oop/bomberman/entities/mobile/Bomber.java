package uet.oop.bomberman.entities.mobile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bomber extends Entity {
    private boolean moving = false;
    private double speed = 1;
    private int spaceStep = 5;
    private int way;

    private static Sprite prevSprite = null;
    static private int countdown = 0;

    private int x1, y1, x2, y2, x3, y3;

    private List<Bom> bombs = new ArrayList<>();

    public Bomber(int xx, int yy, Image img) {
        super(xx, yy, img);
        x1 = x + 16 * 2;    y1 = y;
        x2 = x;         y2 = y + 24 * 2;
        x3 = x1;        y3 = y2;
        way = (int) (this.speed * spaceStep);
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void moveUp() {
        if (countdown == 0) {
            countdown = 3;
            if (Sprite.player_up == prevSprite) {
                prevSprite = Sprite.player_up_1;
            } else if (Sprite.player_up_1 == prevSprite) {
                prevSprite = Sprite.player_up_2;
            } else {
                prevSprite = Sprite.player_up;
            }
        }
        countdown--;
        this.setImg(Bomber.prevSprite.getFxImage());

        if (!checkCollision(x2 / Sprite.SCALED_SIZE, (y2 - 10 * 2 - way) / Sprite.SCALED_SIZE)
                && !checkCollision(x3 / Sprite.SCALED_SIZE, (y3 - 10 * 2 - way) / Sprite.SCALED_SIZE)) {
            y -= way; y1 = y; y2 -= way; y3 = y2;
        } else {
            y2 = (int) ((y2 - 10 * 2) / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE + 10 * 2;
            y = y2 - 24 * 2;
            y1 = y;
            y3 = y2;
        }
    }

    public void moveDown() {
        if (countdown == 0) {
            countdown = 3;
            if (Sprite.player_down == prevSprite) {
                prevSprite = Sprite.player_down_1;
            } else if (Sprite.player_down_1 == prevSprite) {
                prevSprite = Sprite.player_down_2;
            } else {
                prevSprite = Sprite.player_down;
            }
        }
        countdown--;
        this.setImg(Bomber.prevSprite.getFxImage());

        if (!checkCollision(x2 / Sprite.SCALED_SIZE, (y2 + way) / Sprite.SCALED_SIZE)
                && !checkCollision(x3 / Sprite.SCALED_SIZE, (y3 + way) / Sprite.SCALED_SIZE)) {
            y += way; y1 = y; y2 += way; y3 = y2;
        } else {
            y2 = ((int)(y2 / Sprite.SCALED_SIZE) + 1) * Sprite.SCALED_SIZE - 1;
            y = y2 - 24 * 2;
            y1 = y;
            y3 = y2;
        }
    }

    public void moveRight() {
        if (countdown == 0) {
            countdown = 3;
            if (Sprite.player_right == prevSprite) {
                prevSprite = Sprite.player_right_1;
            } else if (Sprite.player_right_1 == prevSprite) {
                prevSprite = Sprite.player_right_2;
            } else {
                prevSprite = Sprite.player_right;
            }
        }
        countdown--;
        this.setImg(Bomber.prevSprite.getFxImage());

        if (!checkCollision((x3 + way) / Sprite.SCALED_SIZE, (y3 - 10) / Sprite.SCALED_SIZE)
                && !checkCollision((x3 + way) / Sprite.SCALED_SIZE, y3 / Sprite.SCALED_SIZE)) {
            x += way; x1 += way; x2 = x; x3 = x1;
        } else {
            x3 = ((int) (x3 / Sprite.SCALED_SIZE) + 1) * Sprite.SCALED_SIZE - 1;
            x = x3 - 16 * 2;
            x1 = x3;
            x2 = x;

        }
    }

    public void moveLeft() {
        if (countdown == 0) {
            countdown = 3;
            if (Sprite.player_left == prevSprite) {
                prevSprite = Sprite.player_left_1;
            } else if (Sprite.player_left_1 == prevSprite) {
                prevSprite = Sprite.player_left_2;
            } else {
                prevSprite = Sprite.player_left;
            }
        }
        countdown--;
        this.setImg(Bomber.prevSprite.getFxImage());

        if (!checkCollision((x2 - way) / Sprite.SCALED_SIZE, (y2 - 10) / Sprite.SCALED_SIZE)
                && !checkCollision((x2 - way) / Sprite.SCALED_SIZE, y2 / Sprite.SCALED_SIZE)) {
            x -= way; x1 -= way; x2 = x; x3 = x1;
        } else {
            x2 = (int) (x2 / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE;
            x = x2;
            x1 = x + 16 * 2;
            x3 = x1;
        }
    }

    public void undou(KeyEvent ke, Bomber bomber) {
        switch (ke.getCode()) {
            case UP:
            case W:
                if(!moving) {
                    bomber.setImg(Sprite.player_up.getFxImage());
                    countdown = 0;
                    prevSprite = Sprite.player_up;
                }
                else bomber.moveUp();
                break;

            case DOWN:
            case S:
                if(!moving) {
                    bomber.setImg(Sprite.player_down.getFxImage());
                    countdown = 0;
                    prevSprite = Sprite.player_down;
                }
                else bomber.moveDown();
                break;

            case LEFT:
            case A:
                if(!moving) {
                    bomber.setImg(Sprite.player_left.getFxImage());
                    countdown = 0;
                    prevSprite = Sprite.player_left;
                }
                else bomber.moveLeft();
                break;

            case RIGHT:
            case D:
                if(!moving) {
                    bomber.setImg(Sprite.player_right.getFxImage());
                    countdown = 0;
                    prevSprite = Sprite.player_right;
                }
                else bomber.moveRight();
                break;

            case SPACE:
            case SHIFT:
                Bom bom = new Bom(bomber.getX() / Sprite.SCALED_SIZE, bomber.getY() / Sprite.SCALED_SIZE, Sprite.bomb.getFxImage());
//                if (bombs.size() == 0) {
//                    bombs.add(bom);
//                }
                bombs.add(bom);
                BombermanGame.mainMap[bomber.getY() / Sprite.SCALED_SIZE][bomber.getX() / Sprite.SCALED_SIZE] = 'b';
                break;
            default:

        }
    }

    public boolean checkCollision(int nextStepX, int nextStepY) {
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
