package uet.oop.bomberman.graphics;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.*;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Lưu trữ thông tin các pixel của 1 sprite (hình ảnh game)
 */
public class Sprite {
    public static BufferedImage buffImage;
    public int x, y, width, height;
    public BufferedImage bfiSprite;
    public Image imgSprite;
    public static final int SCALED_SIZE = 50;
    private static final int TRANSPARENT_COLOR = 0xffff00ff;

    static {
        try {
            buffImage = ImageIO.read(new File("res/textures/remake.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Sprite(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bfiSprite = buffImage.getSubimage(x, y, width, height);
        imgSprite = SwingFXUtils.toFXImage(bfiSprite, null);
        imgSprite = makeTransparent(imgSprite);
    }

    /*
    |--------------------------------------------------------------------------
    | Board sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite grass = new Sprite(125, 110, 25, 25);
    public static Sprite brick = new Sprite(175, 110, 25, 25);
    public static Sprite wall = new Sprite(75, 135, 25, 25);
    public static Sprite portal = new Sprite(175, 160, 25, 25);

    /*
    |--------------------------------------------------------------------------
    | Bomber Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite player_up = new Sprite(70, 1, 16, 25);
    public static Sprite player_down = new Sprite(69, 27, 16, 25);
    public static Sprite player_left = new Sprite(0, 26, 16, 25);
    public static Sprite player_right = new Sprite(103, 27, 16, 25);

    public static Sprite player_up_1 = new Sprite(54, 1, 16, 25);
    public static Sprite player_up_2 = new Sprite(86, 1, 16, 25);

    public static Sprite player_down_1 = new Sprite(53, 27, 16, 25);
    public static Sprite player_down_2 = new Sprite(85, 27, 16, 25);

    public static Sprite player_left_1 = new Sprite(16, 26, 16, 25);
    public static Sprite player_left_2 = new Sprite(33, 26, 16, 25);

    public static Sprite player_right_1 = new Sprite(119, 27, 16, 25);
    public static Sprite player_right_2 = new Sprite(135, 27, 16, 25);

    public static Sprite player_dead1 = new Sprite(26, 56, 16, 25);
    public static Sprite player_dead2 = new Sprite(46, 56, 16, 25);
    public static Sprite player_dead3 = new Sprite(63, 56, 16, 25);
    public static Sprite player_dead4 = new Sprite(80, 56, 16, 25);
    public static Sprite player_dead5 = new Sprite(97, 56, 16, 25);
    public static Sprite player_dead6 = new Sprite(115, 56, 16, 25);

    /*
    |--------------------------------------------------------------------------
    | Character
    |--------------------------------------------------------------------------
     */
    //BALLOM
    public static Sprite balloom_up1 = new Sprite(141, 186, 17, 25);
    public static Sprite balloom_up2 = new Sprite(158, 186, 17, 25);
    public static Sprite balloom_up3 = new Sprite(175, 186, 17, 25);

    public static Sprite balloom_down1 = new Sprite(71, 186, 17, 25);
    public static Sprite balloom_down2 = new Sprite(88, 186, 17, 25);
    public static Sprite balloom_down3 = new Sprite(105, 186, 17, 25);

    public static Sprite balloom_left1 = new Sprite(1, 186, 17, 25);
    public static Sprite balloom_left2 = new Sprite(18, 186, 17, 25);
    public static Sprite balloom_left3 = new Sprite(35, 187, 17, 25);

    public static Sprite balloom_right1 = new Sprite(211, 187, 17, 25);
    public static Sprite balloom_right2 = new Sprite(228, 187, 17, 25);
    public static Sprite balloom_right3 = new Sprite(245, 186, 17, 25);

    public static Sprite balloom_dead = new Sprite(273, 186, 17, 25);

    //ONEAL
    public static Sprite oneal_left1 = new Sprite(2, 218, 19, 25);
    public static Sprite oneal_left2 = new Sprite(21, 219, 19, 25);
    public static Sprite oneal_left3 = new Sprite(40, 219, 19, 25);

    public static Sprite oneal_right1 = new Sprite(211, 219, 19, 25);
    public static Sprite oneal_right2 = new Sprite(230, 219, 19, 25);
    public static Sprite oneal_right3 = new Sprite(249, 218, 19, 25);

    public static Sprite oneal_up1 = new Sprite(141, 219, 19, 25);
    public static Sprite oneal_up2 = new Sprite(161, 219, 19, 25);
    public static Sprite oneal_up3 = new Sprite(181, 219, 19, 25);

    public static Sprite oneal_down1 = new Sprite(71, 216, 19, 25);
    public static Sprite oneal_down2 = new Sprite(91, 217, 19, 25);
    public static Sprite oneal_down3 = new Sprite(111, 217, 19, 25);

    public static Sprite oneal_dead = new Sprite(273, 216, 19, 25);

    //Doll
    public static Sprite doll_left1 = new Sprite(2, 246, 20, 25);
    public static Sprite doll_left2 = new Sprite(25, 247, 20, 25);

    public static Sprite doll_right1 = new Sprite(211, 246, 20, 25);
    public static Sprite doll_right2 = new Sprite(234, 245, 20, 25);

    public static Sprite doll_up1 = new Sprite(141, 248, 20, 25);
    public static Sprite doll_up2 = new Sprite(159, 249, 20, 25);

    public static Sprite doll_down1 = new Sprite(71, 244, 20, 25);
    public static Sprite doll_down2 = new Sprite(92, 245, 20, 25);

    public static Sprite doll_dead = new Sprite(273, 244, 20, 25);

    //Minvo
    public static Sprite minvo_left1 = new Sprite(2, 279, 20, 25);
    public static Sprite minvo_left2 = new Sprite(21, 279, 20, 25);
    public static Sprite minvo_left3 = new Sprite(42, 279, 20, 25);

    public static Sprite minvo_right1 = new Sprite(211, 279, 20, 25);
    public static Sprite minvo_right2 = new Sprite(231, 279, 20, 25);
    public static Sprite minvo_right3 = new Sprite(251, 279, 20, 25);

    public static Sprite minvo_up1 = new Sprite(141, 279, 20, 25);
    public static Sprite minvo_up2 = new Sprite(159, 280, 20, 25);
    public static Sprite minvo_up3 = new Sprite(177, 280, 20, 25);

    public static Sprite minvo_down1 = new Sprite(71, 277, 20, 25);
    public static Sprite minvo_down2 = new Sprite(89, 278, 20, 25);
    public static Sprite minvo_down3 = new Sprite(107, 278, 20, 25);

    public static Sprite minvo_dead = new Sprite(273, 277, 20, 25);

    //ALL
    public static Sprite mob_dead1 = new Sprite(103, 1, 25, 25);
    public static Sprite mob_dead2 = new Sprite(127, 1, 25, 25);
    public static Sprite mob_dead3 = new Sprite(157, 0, 25, 25);

    /*
    |--------------------------------------------------------------------------
    | Bomb Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite bomb = new Sprite(75, 160, 25, 25);
    public static Sprite bomb_1 = new Sprite(100, 160, 25, 25);
    public static Sprite bomb_2 = new Sprite(125, 160, 25, 25);

    /*
    |--------------------------------------------------------------------------
    | FlameSegment Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite bomb_exploded = new Sprite(200, 110, 25, 25);
    public static Sprite bomb_exploded1 = new Sprite(200, 135, 25, 25);
    public static Sprite bomb_exploded2 = new Sprite(200, 160, 25, 25);

    public static Sprite explosion_vertical = new Sprite(350, 135, 25, 25);
    public static Sprite explosion_vertical1 = new Sprite(325, 135, 25, 25);
    public static Sprite explosion_vertical2 = new Sprite(300, 135, 25, 25);

    public static Sprite explosion_horizontal = new Sprite(250, 110, 25, 25);
    public static Sprite explosion_horizontal1 = new Sprite(250, 135, 25, 25);
    public static Sprite explosion_horizontal2 = new Sprite(250, 160, 25, 25);

    public static Sprite explosion_horizontal_left_last = new Sprite(225, 110, 25, 25);
    public static Sprite explosion_horizontal_left_last1 = new Sprite(225, 135, 25, 25);
    public static Sprite explosion_horizontal_left_last2 = new Sprite(225, 160, 25, 25);

    public static Sprite explosion_horizontal_right_last = new Sprite(275, 110, 25, 25);
    public static Sprite explosion_horizontal_right_last1 = new Sprite(275, 135, 25, 25);
    public static Sprite explosion_horizontal_right_last2 = new Sprite(275, 160, 25, 25);

    public static Sprite explosion_vertical_top_last = new Sprite(350, 110, 25, 25);
    public static Sprite explosion_vertical_top_last1 = new Sprite(325, 110, 25, 25);
    public static Sprite explosion_vertical_top_last2 = new Sprite(300, 110, 25, 25);

    public static Sprite explosion_vertical_down_last = new Sprite(350, 160, 25, 25);
    public static Sprite explosion_vertical_down_last1 = new Sprite(325, 160, 25, 25);
    public static Sprite explosion_vertical_down_last2 = new Sprite(300, 160, 25, 25);

    /*
    |--------------------------------------------------------------------------
    | Brick FlameSegment
    |--------------------------------------------------------------------------
     */
    public static Sprite brick_exploded = new Sprite(150, 110, 25, 25);
    public static Sprite brick_exploded1 = new Sprite(150, 135, 25, 25);
    public static Sprite brick_exploded2 = new Sprite(150, 160, 25, 25);

    /*
    |--------------------------------------------------------------------------
    | Powerups
    |--------------------------------------------------------------------------
     */
    public static Sprite powerup_bombs = new Sprite(350, 215, 25, 25);
    public static Sprite powerup_flames = new Sprite(375, 190, 25, 25);
    public static Sprite powerup_speed = new Sprite(325, 215, 25, 25);
    public static Sprite powerup_wallpass = new Sprite(300, 190, 25, 25);
    public static Sprite powerup_detonator = new Sprite(325, 190, 25, 25);
    public static Sprite powerup_bombpass = new Sprite(350, 190, 25, 25);
    public static Sprite powerup_flamepass = new Sprite(300, 215, 25, 25);

    private Image makeTransparent(Image image) {
        final int width = (int) image.getWidth();
        final int height = (int) image.getHeight();
        final PixelReader reader = image.getPixelReader();
        final WritableImage wi = new WritableImage(reader, width, height);
        WritableImage out = new WritableImage(width * 2, height * 2);
        final PixelWriter writer = out.getPixelWriter();
        int argb;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                argb = reader.getArgb(x, y);
                if (reader.getArgb(x, y) == TRANSPARENT_COLOR) argb = 0;
                writer.setArgb(x * 2, y * 2, argb);
                writer.setArgb(x * 2 + 1, y * 2, argb);
                writer.setArgb(x * 2, y * 2 + 1, argb);
                writer.setArgb(x * 2 + 1, y * 2 + 1, argb);
            }
        }
        return out;

    }

    public Image getFxImage() {
        return imgSprite;
    }
}