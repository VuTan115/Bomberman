package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {


    public static int WIDTH;
    public static int HEIGHT;
    public static int LEVEL;

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
        LEVEL = Integer.parseInt(mapSize.get(0));
        HEIGHT = Integer.parseInt(mapSize.get(1));
        WIDTH = Integer.parseInt(mapSize.get(2));

    }

    public void createMap() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("res/levels/Level1.txt"));
            System.out.println(lines);
            getMapSize(lines.get(0));
            lines.remove(0);
            lines.forEach(line -> {
                for (int i = 0; i < WIDTH; i++) {
                    for (int j = 0; j < HEIGHT; j++) {
                        Entity object;
                        if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                            object = new Wall(i, j, Sprite.wall.getFxImage());
                        } else {
                            object = new Grass(i, j, Sprite.grass.getFxImage());
                        }
                        stillObjects.add(object);
                    }
                }
            });
            System.out.println(lines);
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
