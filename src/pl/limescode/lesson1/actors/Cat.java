package pl.limescode.lesson1.actors;

public class Cat implements Moveable {
    private final int RUN_LIMIT = 20;
    private final int JUMP_LIMIT = 15;
    private String nickname;

    public Cat(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public int jump() {
        System.out.println("Cat " + nickname + " is trying to jump");
        return JUMP_LIMIT;
    }

    @Override
    public int run() {
        System.out.println("Cat " + nickname + " is trying to run");
        return RUN_LIMIT;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "nickname='" + nickname + '\'' +
                '}';
    }
}
