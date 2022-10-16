package uet.oop.bomberman.entities;

public abstract class Interaction {
    public static boolean collision(Entity a, Entity b) {
        if (!a.getBoundary().intersects(b.getBoundary())) return false;
        return true;
    }
}
