package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.level.Game.camera;

public abstract class Entity {

    protected double x;
    protected double y;
    protected double width;

    protected boolean isLife = true;
    protected int nodeNumber;
    protected Image img;

    protected long frame = 0;
    protected double height;
    protected boolean collision = false;
    protected boolean flag = false;
    public boolean isLife() {
        return isLife;
    }

    public void setLife(boolean life) {
        isLife = life;
    }

    public int getNodeNumber() {
        return nodeNumber;
    }

    public Entity() {
    }

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(double xUnit, double yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        width = img.getWidth();
        height = img.getHeight();
        nodeNumber = 0;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public boolean isFlag() {
        return flag;
    }


    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public void render(GraphicsContext gc) {
        double renderX = x - camera.getCameraX();
        double renderY = y - camera.getCameraY();
        gc.drawImage(img, renderX, renderY);
    }

    public abstract void update();

    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, width, height);
    }
}
