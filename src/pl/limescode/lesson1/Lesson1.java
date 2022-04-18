package pl.limescode.lesson1;

import pl.limescode.lesson1.actors.Cat;
import pl.limescode.lesson1.actors.Human;
import pl.limescode.lesson1.actors.Moveable;
import pl.limescode.lesson1.actors.Robot;
import pl.limescode.lesson1.obstacles.Obstacle;
import pl.limescode.lesson1.obstacles.Track;
import pl.limescode.lesson1.obstacles.Wall;

public class Lesson1 {

    public static void main(String[] args) {

        Moveable cat1 = new Cat("Murka");
        Moveable human1 = new Human("Alexander");
        Moveable robot1 = new Robot("R2-D2");
        Obstacle track = new Track(10);
        Obstacle wall = new Wall(20);

        testObstacle(track, cat1, human1, robot1);
        testObstacle(wall, cat1, human1, robot1);

        Moveable cat2 = new Cat("Vasilisa");
        Moveable human2 = new Human("Ignat");
        Moveable robot2 = new Robot("UFO-48");

        Moveable[] moveables = {cat1, cat2, human1, human2, robot1, robot2};
        Obstacle[] obstacles = {track, wall};
        testObstacles(obstacles, moveables);
    }

    private static void testObstacle(Obstacle obstacle, Moveable... moveables) {
        System.out.println("==== Testing single obstacle ====");
        for (Moveable moveable : moveables) {
            System.out.println(moveable);
            System.out.println(obstacle.overcome(moveable));
            System.out.println("---");
        }
    }

    private static void testObstacles(Obstacle[] obstacles, Moveable[] moveables) {
        System.out.println("==== Testing multiple obstacles ====");
        for (Moveable moveable : moveables) {
            System.out.println(moveable);
            for (Obstacle obstacle : obstacles) {
                var isOvercomed = obstacle.overcome(moveable);
                System.out.println(isOvercomed);
                if (!isOvercomed)
                    break;
            }
            System.out.println("---");
        }
    }

}
