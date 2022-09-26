package uet.oop.bomberman.entities;

import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.user_input.Keyboard;

public class Bomber extends Entity {
    int xVec = 0;
    int yVec = 0;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {
        x += xVec;
        y += yVec;


        if( x + Sprite.DEFAULT_SIZE >= Sprite.SCALED_SIZE * 20 - 32 || x < 16 + 12) {
            x -= xVec;
        }
        if( y + Sprite.DEFAULT_SIZE >= Sprite.SCALED_SIZE * 15 - 45 || y < 16 + 12) {
            y -= yVec;
        }
    }

    public void move(Keyboard a) {
        if(a.keys[0]) {
            yVec = -3;
        }
        else if(a.keys[1]) {
            yVec = +3;
        }
        else if(a.keys[2]) {
            xVec = -3;
        }
        else if(a.keys[3]) {
            xVec = 3;
        } else {
            xVec = 0;
            yVec = 0;
        }
    }
}
