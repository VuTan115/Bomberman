package uet.oop.bomberman.entities.BuffItems;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class Items extends Entity {
    public Items() {
    }
    public abstract void eatItems();
    public Items(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
}
