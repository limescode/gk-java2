package pl.limescode.lesson1.actors;

public class Robot implements Moveable {

    private final int JUMP_LIMIT = 35;
    private final int RUN_LIMIT = 30;
    private String model;

    public Robot(String model) {
        this.model = model;
    }

    @Override
    public int jump() {
        System.out.println("Robot " + model + " is trying to jump");
        return JUMP_LIMIT;
    }

    @Override
    public int run() {
        System.out.println("Robot " + model + " is trying to run");
        return RUN_LIMIT;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "model='" + model + '\'' +
                '}';
    }
}
