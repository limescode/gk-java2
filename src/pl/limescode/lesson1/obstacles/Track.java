package pl.limescode.lesson1.obstacles;

import pl.limescode.lesson1.actors.Moveable;

public class Track implements Obstacle {

    private final int distance;

    public Track(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean overcome(Moveable moveable) {
        int runDistance = moveable.run();
        return runDistance >= distance;
    }
}
