package pl.limescode.lesson1.actors;

public class Human implements Moveable {
    private final int RUN_LIMIT = 20;
    private final int JUMP_LIMIT = 25;
    private String name;

    public Human(String name) {
        this.name = name;
    }

    @Override
    public int jump() {
        System.out.println("Human " + name + " is trying to jump");
        return JUMP_LIMIT;
    }

    @Override
    public int run() {
        System.out.println("Human " + name + " is trying to run");
        return RUN_LIMIT;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                '}';
    }
}
