package uet.oop.bomberman.entities;

import uet.oop.bomberman.entities.Entity;
import static uet.oop.bomberman.BombermanGame.idObjects;

public class Blocked {

    public static boolean block_down(Entity entity) {   //Create a blocked that prevent all mob go down through the object
        return idObjects[entity.getX() / 32][entity.getY() / 32 + 1] == 0;
    }

    public static boolean block_up(Entity entity) {     //Create a blocked that prevent all mob go up through the object
        return idObjects[entity.getX() / 32][entity.getY() / 32 - 1] == 0;
    }

    public static boolean block_left(Entity entity) {   //Create a blocked that prevent all mob go left through the object
        return idObjects[entity.getX() / 32 - 1][entity.getY() / 32] == 0;
    }

    public static boolean block_right(Entity entity) {   //Create a blocked that prevent all mob go right through the object
        return idObjects[entity.getX() / 32 + 1][entity.getY() / 32] == 0;
    }

    public static boolean block_down_bomb(Entity entity, int power) {   //Limit the scope and animation of the explosion downward
        return idObjects[entity.getX() / 32][entity.getY() / 32 + 1 + power] == 0
                || idObjects[entity.getX() / 32][entity.getY() / 32 + 1 + power] == 3
                || idObjects[entity.getX() / 32][entity.getY() / 32 + 1 + power] == 6
                || idObjects[entity.getX() / 32][entity.getY() / 32 + 1 + power] == 7
                || idObjects[entity.getX() / 32][entity.getY() / 32 + 1 + power] == 8;
    }

    public static boolean block_up_bomb(Entity entity, int power) {     //Limit the scope and animation of the explosion upward
        return idObjects[entity.getX() / 32][entity.getY() / 32 - 1 - power] == 0
                || idObjects[entity.getX() / 32][entity.getY() / 32 - 1 - power] == 3
                || idObjects[entity.getX() / 32][entity.getY() / 32 - 1 - power] == 6
                || idObjects[entity.getX() / 32][entity.getY() / 32 - 1 - power] == 7
                || idObjects[entity.getX() / 32][entity.getY() / 32 - 1 - power] == 8;
    }

    public static boolean block_left_bomb(Entity entity, int power) {   //Limit the scope and animation of the explosion to the left
        return idObjects[entity.getX() / 32 - 1 - power][entity.getY() / 32] == 0
                || idObjects[entity.getX() / 32 - 1 - power][entity.getY() / 32] == 3
                || idObjects[entity.getX() / 32 - 1 - power][entity.getY() / 32] == 6
                || idObjects[entity.getX() / 32 - 1 - power][entity.getY() / 32] == 7
                || idObjects[entity.getX() / 32 - 1 - power][entity.getY() / 32] == 8;
    }

    public static boolean block_right_bomb(Entity entity, int power) {      //Limit the scope and animation of the explosion to the right
        return idObjects[entity.getX() / 32 + 1 + power][entity.getY() / 32] == 0
                || idObjects[entity.getX() / 32 + 1 + power][entity.getY() / 32] == 3
                || idObjects[entity.getX() / 32 + 1 + power][entity.getY() / 32] == 6
                || idObjects[entity.getX() / 32 + 1 + power][entity.getY() / 32] == 7
                || idObjects[entity.getX() / 32 + 1 + power][entity.getY() / 32] == 8;
    }
}
