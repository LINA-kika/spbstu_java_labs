public class Hero {
    private final String name;
    private MoveBehavior moveBehavior;

    public Hero(String hName) {
        this.name = hName;
    }

    public void setMoveBehavior(MoveBehavior moveBehavior) {
        this.moveBehavior = moveBehavior;
    }

    public void performMove() {
        System.out.println("===============================");
        System.out.println(this.name + moveBehavior.move());
        System.out.println("===============================");
    }
}
