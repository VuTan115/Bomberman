package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.BuffItems.PowerupSpeed;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Player.Bomber;
import uet.oop.bomberman.entities.hasenai.Brick;
import uet.oop.bomberman.entities.hasenai.Grass;
import uet.oop.bomberman.entities.hasenai.Portal;
import uet.oop.bomberman.entities.hasenai.Wall;
import uet.oop.bomberman.entities.mobile.Bomb.Flame;
import uet.oop.bomberman.entities.mobile.Nomster.Balloon;
import uet.oop.bomberman.entities.mobile.Nomster.Oneal;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BombermanGame extends Application {
    public static int WIDTH;
    public static int HEIGHT;
    public static int LEVEL;

    public static char[][] mainMap;

    private GraphicsContext gc;
    private Canvas canvas;
    private Bomber bomber;
    public static List<Entity> flame = new ArrayList<>();
    public static List<Entity> entities = new ArrayList<>();
    private List<Entity> dynamicObject = new ArrayList<>();
    private List<Entity> buffItems = new ArrayList<>();
    public List<Entity> stillObjects = new ArrayList<>();
    private List<Grass> grass = new ArrayList<>();

    public BombermanGame() {
    }

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        createMap();
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        Group root = new Group();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Bomberman");
        stage.getIcons().add(new Image("/textures/gameLogo.jpg"));
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };

        timer.start();
        bomber = new Bomber(1, 1, Sprite.player_right.getFxImage());

        scene.setOnKeyPressed(ke -> {
            bomber.initKeyCode(ke);
        });
        scene.setOnKeyReleased(ke -> {
            bomber.stopped(ke);
        });
    }

    public void getMapSize(String str) {
        List<String> mapSize = new ArrayList<>();
        String size = "";
        int len = str.length();
        for (int i = 0; i < len; i++) {
            size += str.charAt(i);
            if (str.charAt(i) == ' ' || i == len - 1) {
                mapSize.add(size.trim());
                size = "";
            }
        }
        LEVEL = Integer.parseInt(mapSize.get(0));//1
        HEIGHT = Integer.parseInt(mapSize.get(1));//13
        WIDTH = Integer.parseInt(mapSize.get(2));//31
        mainMap = new char[HEIGHT][WIDTH];

    }


//  still buggg
    public void transferTxtFileToMap(String str, int height) {
        int length = str.length();
        Entity object = null;
        for (int i = 0; i < length; i++) {
            mainMap[height][i] = str.charAt(i);
            grass.add(new Grass(i, height, Sprite.grass.getFxImage()));
            switch (str.charAt(i)) {
                case '#': {
                    object = new Wall(i, height, Sprite.wall.getFxImage());
                    stillObjects.add(object);
                    entities.add(object);
                    break;
                }

                case '*': {
                    object = new Brick(i, height, Sprite.brick.getFxImage());
                    stillObjects.add(object);
                    entities.add(object);
                    break;
                }

                case 'x': {
                    object = new Portal(i, height, Sprite.brick.getFxImage());
                    stillObjects.add(object);
                    entities.add(object);
                    break;
                }

                case '1': {
                    object = new Balloon(i, height);
                    dynamicObject.add(object);
                    entities.add(object);
                    break;
                }

                case '2': {
                    object = new Oneal(i, height, Sprite.oneal_left1.getFxImage());
                    dynamicObject.add(object);
                    entities.add(object);
                    break;
                }
                case 'f': {
                    object = new Flame(i, height, Sprite.powerup_flames.getFxImage());
                    Bomber.flame.add(object);
                    entities.add(object);
                    break;
                }
                case 's': {
                    object = new PowerupSpeed(i, height, Sprite.brick.getFxImage());
                    buffItems.add(object);
                    break;
                }

            }
        }
    }

    public void createMap() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("res/levels/Level1.txt"));
            getMapSize(lines.get(0));
            lines.remove(0);
            AtomicInteger height = new AtomicInteger();
            lines.forEach(line -> {
                transferTxtFileToMap(line, height.get());
                height.incrementAndGet();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        entities.forEach(Entity::update);
        bomber.update();

    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        grass.forEach(g -> g.render(gc));
        flame.forEach(g -> g.render(gc));
        dynamicObject.forEach(g -> g.render(gc));
        stillObjects.forEach(g -> g.render(gc));
        bomber.render(gc);
    }
}
