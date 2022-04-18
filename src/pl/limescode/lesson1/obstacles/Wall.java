package pl.limescode.lesson1.obstacles;

import pl.limescode.lesson1.actors.Moveable;

public class Wall implements Obstacle {

    private final int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public boolean overcome(Moveable moveable) {
        int jumpHeight = moveable.jump();
        return jumpHeight >= height;
    }
}
