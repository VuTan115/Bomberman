package uet.oop.bomberman.entities.mobile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.mobile.Bomb.Bom;
import uet.oop.bomberman.graphics.Sprite;

import java.util.*;

public class Bomber extends Entity {
    private double speed = 1;
    private int spaceStep = 5;
    private int way;
    private int ctd = 0;
    private List<KeyCode> keyCodeUsing = new ArrayList<>();

    private Sprite prevSprite = null;
    private List<Sprite> deadSprites = new ArrayList<>();

    private List<Bom> bombs = new ArrayList<>();

    public Bomber(int xx, int yy, Image img) {
        super(xx, yy, img);
        x1 = x + 16 * 2 - 3;
        y1 = y;
        x2 = x;
        y2 = y + 24 * 2;
        x3 = x1 - 3;
        y3 = y2;
        way = (int) (this.speed * spaceStep);
        deadSprites.add(Sprite.player_dead6);
        deadSprites.add(Sprite.player_dead5);
        deadSprites.add(Sprite.player_dead4);
        deadSprites.add(Sprite.player_dead3);
        deadSprites.add(Sprite.player_dead2);
        deadSprites.add(Sprite.player_dead1);
    }

    public void moveUp() {
        if (keyCodeUsing.get(0) == KeyCode.UP || keyCodeUsing.get(0) == KeyCode.W) {
            if (countdown == 0) {
                countdown = 3;

                if (Sprite.player_up == prevSprite) {
                    Sound.play(Sound.footStep1, 0);
                    prevSprite = Sprite.player_up_1;
                } else if (Sprite.player_up_1 == prevSprite) {

                    prevSprite = Sprite.player_up_2;
                } else {
                    prevSprite = Sprite.player_up;
                }
            }
            countdown--;
            this.setImg(prevSprite.getFxImage());
        }
        if (!checkCollision(x2 / Sprite.SCALED_SIZE,
                (y2 - 10 * 2 - way) / Sprite.SCALED_SIZE)
                && !checkCollision(x3 / Sprite.SCALED_SIZE,
                (y3 - 10 * 2 - way) / Sprite.SCALED_SIZE)) {
            y -= way;
            y1 = y;
            y2 -= way;
            y3 = y2;
        } else {
            y2 = (int) ((y2 - 10 * 2) / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE + 10 * 2;
            y = y2 - 24 * 2;
            y1 = y;
            y3 = y2;
        }
    }

    public void moveDown() {
        if (keyCodeUsing.get(0) == KeyCode.DOWN || keyCodeUsing.get(0) == KeyCode.S) {
            if (countdown == 0) {
                countdown = 3;
                if (Sprite.player_down == prevSprite) {
                    prevSprite = Sprite.player_down_1;
                } else if (Sprite.player_down_1 == prevSprite) {
                    Sound.play(Sound.footStep1,0);
                    prevSprite = Sprite.player_down_2;
                } else {
                    prevSprite = Sprite.player_down;
                }
            }
            countdown--;
            this.setImg(prevSprite.getFxImage());
        }
        if (!checkCollision(x2 / Sprite.SCALED_SIZE,
                (y2 + way) / Sprite.SCALED_SIZE)
                && !checkCollision(x3 / Sprite.SCALED_SIZE,
                (y3 + way) / Sprite.SCALED_SIZE)) {
            y += way;
            y1 = y;
            y2 += way;
            y3 = y2;
        } else {
            y2 = ((int) (y2 / Sprite.SCALED_SIZE) + 1) * Sprite.SCALED_SIZE - 1;
            y = y2 - 24 * 2;
            y1 = y;
            y3 = y2;
        }
    }

    public void moveRight() {
        if (keyCodeUsing.get(0) == KeyCode.RIGHT || keyCodeUsing.get(0) == KeyCode.D) {
            if (countdown == 0) {
                countdown = 3;
                if (Sprite.player_right == prevSprite) {
                    Sound.play(Sound.footStep1,0);
                    prevSprite = Sprite.player_right_1;
                } else if (Sprite.player_right_1 == prevSprite) {
                    prevSprite = Sprite.player_right_2;
                } else {
                    prevSprite = Sprite.player_right;
                }
            }
            countdown--;
            this.setImg(prevSprite.getFxImage());
        }
        if (!checkCollision((x3 + way) /
                Sprite.SCALED_SIZE, (y3 - 10 * 2) / Sprite.SCALED_SIZE)
                && !checkCollision((x3 + way) / Sprite.SCALED_SIZE, y3 / Sprite.SCALED_SIZE)) {
            x += way;
            x1 += way;
            x2 = x;
            x3 = x1;
        } else {
            x3 = ((int) (x3 / Sprite.SCALED_SIZE) + 1) * Sprite.SCALED_SIZE - 1;
            x = x3 - 16 * 2 + 3;
            x1 = x3;
            x2 = x;

        }
    }

    public void moveLeft() {
        if (keyCodeUsing.get(0) == KeyCode.LEFT || keyCodeUsing.get(0) == KeyCode.A) {
            if (countdown == 0) {
                countdown = 3;
                if (Sprite.player_left == prevSprite) {

                    prevSprite = Sprite.player_left_1;
                } else if (Sprite.player_left_1 == prevSprite) {
                    Sound.play(Sound.footStep1,0);
                    prevSprite = Sprite.player_left_2;
                } else {
                    prevSprite = Sprite.player_left;
                }
            }
            countdown--;
            this.setImg(prevSprite.getFxImage());
        }
        if (!checkCollision((x2 - way) / Sprite.SCALED_SIZE,
                (y2 - 10 * 2) / Sprite.SCALED_SIZE)
                && !checkCollision((x2 - way) / Sprite.SCALED_SIZE,
                y2 / Sprite.SCALED_SIZE)) {
            x -= way;
            x1 -= way;
            x2 = x;
            x3 = x1;
        } else {
            x2 = (int) (x2 / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE;
            x = x2;
            x1 = x + 16 * 2 - 3;
            x3 = x1;
        }
    }

    @Override
    protected boolean inTheArea(Entity entity) {
        int a1 = entity.getX();
        int a2 = entity.getY();
        int b1 = (int) (a1 + img.getWidth());
        int b2 = (int) (a2 + img.getHeight());
        return (a1 <= x && x <= b1
                && a2 <= y + 30 && y + 30 <= b2)
                || (a1 <= x1 && x1 <= b1
                && a2 <= y1 + 30 && y1 + 30 <= b2)
                || (a1 <= x2 && x2 <= b1
                && a2 <= y2 && y2 <= b2)
                || (a1 <= x3 && x3 <= b1
                && a2 <= y3 && y3 <= b2);
    }

    public void stopped(KeyEvent ke) {
        if (dead) return;
        keyCodeUsing.remove(ke.getCode());
        if (keyCodeUsing.isEmpty()) {
            switch (ke.getCode()) {
                case UP:
                case W:
                    this.setImg(Sprite.player_up.getFxImage());
                    countdown = 0;
                    prevSprite = Sprite.player_up;
                    break;
                case DOWN:
                case S:
                    this.setImg(Sprite.player_down.getFxImage());
                    countdown = 0;
                    prevSprite = Sprite.player_down;
                    break;
                case LEFT:
                case A:
                    this.setImg(Sprite.player_left.getFxImage());
                    countdown = 0;
                    prevSprite = Sprite.player_left;
                    break;
                case RIGHT:
                case D:
                    this.setImg(Sprite.player_right.getFxImage());
                    countdown = 0;
                    prevSprite = Sprite.player_right;
                    break;
            }
        }
    }

    public void initKeyCode(KeyEvent ke) {
        if (!keyCodeUsing.contains(ke.getCode()) && keyCodeUsing.size() < 2
                && (ke.getCode() == KeyCode.UP || ke.getCode() == KeyCode.DOWN
                || ke.getCode() == KeyCode.LEFT || ke.getCode() == KeyCode.RIGHT
                || ke.getCode() == KeyCode.W || ke.getCode() == KeyCode.S
                || ke.getCode() == KeyCode.A || ke.getCode() == KeyCode.D)) {
            keyCodeUsing.add(ke.getCode());
        }
        if (ke.getCode() == KeyCode.SPACE) {
            int xUnit = ((x2 + x3) / 2) / Sprite.SCALED_SIZE;
            int yUnit = ((y2 + y) / 2) / Sprite.SCALED_SIZE;
            if (BombermanGame.mainMap[yUnit][xUnit] == ' ') {
                Bom bom = new Bom(xUnit, yUnit, Sprite.bomb.getFxImage());
                bombs.add(bom);
                BombermanGame.mainMap[yUnit][xUnit] = 'b';
            }
        }
    }

    public void undou() {

        boolean boo = false;
        if (!bombs.isEmpty()) boo = inTheArea(bombs.get(bombs.size() - 1));
        if (boo) {
            BombermanGame.mainMap[toU(bombs.get(bombs.size() - 1).getY())][toU(bombs.get(bombs.size() - 1).getX())] = ' ';
        }
        for (int i = keyCodeUsing.size() - 1; i >= 0; i--)
            switch (keyCodeUsing.get(i)) {
                case UP:
                case W:
                    this.moveUp();
                    break;

                case DOWN:
                case S:
                    this.moveDown();
                    break;

                case LEFT:
                case A:
                    this.moveLeft();
                    break;

                case RIGHT:
                case D:
                    this.moveRight();
                    break;
            }
        if (boo) {
            BombermanGame.mainMap[toU(bombs.get(bombs.size() - 1).getY())][toU(bombs.get(bombs.size() - 1).getX())] = 'b';
        }
    }

    @Override
    public boolean checkCollision(int nextStepX, int nextStepY) {
        if (BombermanGame.mainMap[nextStepY][nextStepX] == '1'
                || BombermanGame.mainMap[nextStepY][nextStepX] == '2'
                || BombermanGame.mainMap[nextStepY][nextStepX] == '3'
                || BombermanGame.mainMap[nextStepY][nextStepX] == '4'
                || BombermanGame.mainMap[nextStepY][nextStepX] == 'f') dead = true;
        return BombermanGame.mainMap[nextStepY][nextStepX] != ' ';
    }

    @Override
    public void fixedUpdate30() {
        if (!dead) {
            undou();
            if (ctd++ == 0) {
                bombs.forEach(Entity::update);
            } else if (ctd == 3) ctd = 0;
        }
    }

    @Override
    public void fixedUpdate500() {
        if (dead) {
            playerDead();
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        checkCollision(toU((x + x1) / 2), toU((y + y2) / 2));
        bombs.forEach(g -> g.render(gc));
        super.render(gc);
    }

    private void playerDead() {

        if (deadSprites.isEmpty()) {
            try {
                Sound.play(Sound.DEAD,0);
                Thread.sleep(1000);
                System.exit(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        setImg(deadSprites.get(deadSprites.size() - 1).getFxImage());
        deadSprites.remove(deadSprites.get(deadSprites.size() - 1));
    }
}
