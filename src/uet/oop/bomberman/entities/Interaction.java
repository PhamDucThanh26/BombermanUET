package uet.oop.bomberman.entities;

public abstract class Interaction {
    public static boolean collision(Entity a, Entity b) {
        return a.getBoundary().intersects(b.getBoundary());
    }
}
