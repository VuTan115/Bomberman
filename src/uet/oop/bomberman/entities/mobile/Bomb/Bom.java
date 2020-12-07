package uet.oop.bomberman.entities.mobile.Bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.mobile.Sound;
import uet.oop.bomberman.graphics.Sprite;

public class Bom extends Entity {
    Sprite prevSprite = Sprite.bomb;
    private Flame[][] flames;
    private int flameLength = 1;
    private int cur = 2;
    private int countdown = 15;
    long time500 = 0;

    private static Sprite[] mid = {Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2};
    private static Sprite[][] hor =
            {{Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal, Sprite.explosion_horizontal_right_last},
                    {Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal1, Sprite.explosion_horizontal_right_last1},
                    {Sprite.explosion_horizontal_left_last2, Sprite.explosion_horizontal2, Sprite.explosion_horizontal_right_last2}};

    private static Sprite[][] ver =
            {{Sprite.explosion_vertical_top_last, Sprite.explosion_vertical, Sprite.explosion_vertical_down_last},
                    {Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical1, Sprite.explosion_vertical_down_last1},
                    {Sprite.explosion_vertical_top_last2, Sprite.explosion_vertical2, Sprite.explosion_vertical_down_last2}};

    public void setFlameLength(int flameLength) {
        this.flameLength = flameLength;
        setFlames();
    }

    boolean checkPos(int a, int b) {
        return BombermanGame.mainMap[a][b] != '#'
                && BombermanGame.mainMap[a][b] != '*';
    }

    public void setFlames() {
        int xUnit = toU(x) - flameLength, yUnit = toU(y) - flameLength;
        flames = new Flame[flameLength * 2 + 1][flameLength * 2 + 1];
        for (int i = 0; i <= flameLength * 2; i++) {
            for (int j = 0; j <= flameLength * 2; j++) {
                if (checkPos(yUnit + i,xUnit + j)) {
                    if (i == flameLength) {
                        if (i == j) {
                            flames[i][j] = new Flame(xUnit + j, yUnit + i, mid[cur].getFxImage());
                            BombermanGame.mainMap[yUnit + i][xUnit + j] = 'f';
                        } else if (j == 0) {
                                flames[i][j] = new Flame(xUnit + j, yUnit + i, hor[cur][0].getFxImage());
                                BombermanGame.mainMap[yUnit + i][xUnit + j] = 'f';
                        } else if (j == flameLength * 2) {
                                flames[i][j] = new Flame(xUnit + j, yUnit + i, hor[cur][2].getFxImage());
                                BombermanGame.mainMap[yUnit + i][xUnit + j] = 'f';
                        } else {
                                flames[i][j] = new Flame(xUnit + j, yUnit + i, hor[cur][1].getFxImage());
                                BombermanGame.mainMap[yUnit + i][xUnit + j] = 'f';
                        }
                    }
                    if (j == flameLength) {
                        if (i == 0) {
                                flames[i][j] = new Flame(xUnit + j, yUnit + i, ver[cur][0].getFxImage());
                                BombermanGame.mainMap[yUnit + i][xUnit + j] = 'f';
                        } else if (i == flameLength * 2) {
                                flames[i][j] = new Flame(xUnit + j, yUnit + i, ver[cur][2].getFxImage());
                                BombermanGame.mainMap[yUnit + i][xUnit + j] = 'f';
                        } else {
                                flames[i][j] = new Flame(xUnit + j, yUnit + i, ver[cur][1].getFxImage());
                                BombermanGame.mainMap[yUnit + i][xUnit + j] = 'f';
                        }
                    }
                }
            }
        }
    }

    public Bom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.setImg(Sprite.bomb.getFxImage());
    }

    @Override
    public void fixedUpdate500() {
        if (countdown == -3 || cur == -1) return;
        if (countdown-- <= 0) {
            for (int i = 0; i <= flameLength * 2; i++) {
                for (int j = 0; j <= flameLength * 2; j++) {
                    if (flames[i][j] != null) {
                        if (i == flameLength) {
                            if (i == j) {
                                flames[i][j].setImg(mid[cur].getFxImage());
                            } else if (j == 0) {
                                flames[i][j].setImg(hor[cur][0].getFxImage());
                            } else if (j == flameLength * 2) {
                                flames[i][j].setImg(hor[cur][2].getFxImage());
                            } else {
                                flames[i][j].setImg(hor[cur][1].getFxImage());
                            }
                        }
                        if (j == flameLength) {
                            if (i == 0) {
                                flames[i][j].setImg(ver[cur][0].getFxImage());
                            } else if (i == flameLength * 2) {
                                flames[i][j].setImg(ver[cur][2].getFxImage());
                            } else {
                                flames[i][j].setImg(ver[cur][1].getFxImage());
                            }
                        }
                    }
                }
            }
            cur--;
        }
    }

    @Override
    public void update() {
        if (countdown-- > 0) {
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
        if (countdown == 0 && flames == null) {
            Sound.play(Sound.EXPLORE,0);
            setFlames();
        }
        long time = System.currentTimeMillis();

        if (time500 != time / 500) {
            time500 = time / 500;
            fixedUpdate500();
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if(countdown > 0) super.render(gc);
        else {
            for (Flame[] f:flames) {
                for (Flame fl: f) {
                    if (fl != null) fl.render(gc);
                }
            }
        }
    }
}
