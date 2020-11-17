package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.mobile.*;
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
    private Bom bom;
    private GraphicsContext gc;
    private Canvas canvas;
    private Bomber bomber;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> dynamicObject= new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private List<Bom> bombs = new ArrayList<>();
    private List<Grass> grass = new ArrayList<>();
    public BombermanGame()  {
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
        stage.setTitle("Bomberman ");
        stage.getIcons().add(new Image("file:///E:/Clone/Bomberman/res/textures/Bomberman_Touch_cover_art.png"));
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
        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        bomber = (Bomber) bomberman;
        entities.add(bomber);
        scene.setOnKeyPressed(ke -> {
            switch (ke.getCode()) {
                case UP:
                case W:
                    bomber.moveUp();
                    bomberman.setImg(Sprite.player_up.getFxImage());
                    System.out.println("moveUp");
                    break;

                case DOWN:
                case S:
                    bomber.moveDown();
                    bomberman.setImg(Sprite.player_down.getFxImage());
                    System.out.println("moveDown");
                    break;

                case LEFT:
                case A:
                    bomber.moveLeft();
                    bomberman.setImg(Sprite.player_left.getFxImage());
                    System.out.println("moveLeft");
                    break;

                case RIGHT:
                case D:
                    bomber.moveRight();
                    bomberman.setImg(Sprite.player_right.getFxImage());
                    System.out.println("moveRight");
                    break;
                case SPACE:
                case SHIFT:
                    bom = new Bom(bomber.getX() / Sprite.SCALED_SIZE, bomber.getY() / Sprite.SCALED_SIZE, Sprite.bomb.getFxImage());
                    bombs.add(bom);
                    mainMap[bomberman.getY() / Sprite.SCALED_SIZE][bomberman.getX() / Sprite.SCALED_SIZE] = 'b';
                    System.out.println("create bomb");
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


        for (int i = 0; i < length; i++) {// cot ngang
            mainMap[height][i] = str.charAt(i);
            grass.add(new Grass(i, height, Sprite.grass.getFxImage()));
            switch (str.charAt(i)) {
                case '#': {
                    object = new Wall(i, height, Sprite.wall.getFxImage());
                    stillObjects.add(object);
                    break;
                }
                case '*': {
                    object = new Brick(i, height, Sprite.brick.getFxImage());
                    stillObjects.add(object);
                    break;
                }
                case 'x': {
                    object = new Portal(i, height, Sprite.portal.getFxImage());
                    stillObjects.add(object);
                    break;
                }
                case '1': {
                    object = new Balloon(i, height, Sprite.balloom_left1.getFxImage());
                    dynamicObject.add(object);
                    break;
                }

                case '2': {
                    object = new Oneal(i, height, Sprite.oneal_left1.getFxImage());
                    dynamicObject.add(object);
                    break;
                }
                case 'f': {
                    object = new Flame(i, height, Sprite.powerup_flames.getFxImage());
                    dynamicObject.add(object);
                    break;
                }
                case 's': {
                    object = new SpeedItem(i, height, Sprite.powerup_speed.getFxImage());
                    stillObjects.add(object);
                    break;
                }
                default:
                    object = new Grass(i, height, Sprite.grass.getFxImage());
                    stillObjects.add(object);
            }
        }
    }

    public void createMap() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("res/levels/Level1.txt"));
            getMapSize(lines.get(0));
            //System.out.println(lines);
            lines.remove(0);
            AtomicInteger height = new AtomicInteger(); //cot doc
            lines.forEach(line -> {
                transferTxtFileToMap(line, height.get());
                //grass.add(new Grass(height.get(),0,Sprite.grass.getFxImage()));
                height.incrementAndGet();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        entities.forEach(Entity::update);
        bombs.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        grass.forEach(g -> g.render(gc));
        stillObjects.forEach(g -> g.render(gc));
        dynamicObject.forEach(g -> g.render(gc));
        bombs.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
