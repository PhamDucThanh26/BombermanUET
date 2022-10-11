package uet.oop.bomberman.graphics;

public interface IAnimation {
    int FPS = 60;
    long getCurrentFrame();
    void updateAnimation();
}
