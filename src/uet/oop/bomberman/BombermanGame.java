package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.io.FileNotFoundException;
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
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    public BombermanGame() throws FileNotFoundException {
    }


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        createMap();
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);
        // Tao scene
        Scene scene = new Scene(root);
        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();
        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        bomber = (Bomber) bomberman;
        entities.add(bomber);
        scene.setOnKeyPressed(ke -> {
            switch (ke.getCode()) {
                case UP:
                    //bomberman.setY(bomberman.getY() - Sprite.SCALED_SIZE);
                    bomber.moveUp();
                    bomberman.setImg(Sprite.player_up.getFxImage());

                    System.out.println("moveUp");

                    break;
                case DOWN:
                    //bomberman.setY(bomberman.getY() + Sprite.SCALED_SIZE);
                    bomber.moveDown();
                    bomberman.setImg(Sprite.player_down.getFxImage());
                    System.out.println("moveDown");
                    break;
                case LEFT:
                    //bomberman.setX(bomberman.getX() - Sprite.SCALED_SIZE);
                    bomber.moveLeft();
                    bomberman.setImg(Sprite.player_left.getFxImage());
                    System.out.println("moveLeft");
                    break;
                case RIGHT:
                    //bomberman.setX(bomberman.getX() + Sprite.SCALED_SIZE);
                    bomber.moveRight();
                    bomberman.setImg(Sprite.player_right.getFxImage());
                    System.out.println("moveRight");
                    break;
            }

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

    public void transferTxtFileToMap(String str, int height) {
        int length = str.length();
        Entity object = null;
        for (int i = 0; i < length; i++) {
            mainMap[height][i] = str.charAt(i);
            switch (str.charAt(i)) {
                case '#': {
                    object = new Wall(i, height, Sprite.wall.getFxImage());
                    break;
                }
                case '*': {
                    object = new Brick(i, height, Sprite.brick.getFxImage());
                    break;
                }
                case 'x': {
                    object = new Portal(i, height, Sprite.portal.getFxImage());
                    break;
                }
                case ' ': {
                    object = new Portal(i, height, Sprite.grass.getFxImage());
                    break;
                }
                case '1': {
                    object = new Portal(i, height, Sprite.balloom_left1.getFxImage());
                    break;
                }

                case '2': {
                    object = new Portal(i, height, Sprite.oneal_left1.getFxImage());
                    break;
                }

                case 'b': {
                    object = new Portal(i, height, Sprite.bomb.getFxImage());
                    break;
                }
                case 'f': {
                    object = new Portal(i, height, Sprite.powerup_flames.getFxImage());
                    break;
                }
                case 's': {
                    object = new Portal(i, height, Sprite.powerup_speed.getFxImage());
                    break;
                }

                default:
                    object = new Portal(i, height, Sprite.grass.getFxImage());
            }
            stillObjects.add(object);
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
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
