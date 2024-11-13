import java.util.List;

public class TestClass {

    protected void firstProtectedMethod() {
        System.out.println("First protected method");
    }

    @Repeat(3)
    protected void secondProtectedMethod(String name, int count) {
        System.out.println("Second protected method " + name + ", " + count);
    }

    private void firstPrivateMethod() {
        System.out.println("First private method");
    }

    @Repeat(2)
    private void secondPrivateMethod(List<Integer> nums) {
        System.out.println("Second private method" + nums.toString());
    }

    public void firstPublicMethod() {
        System.out.println("First public method");
    }

    @Repeat()
    public void secondPublicMethod() {
        System.out.println("Second public method");
    }

}
