import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        try {
            RepeatAnnotationInvoker.invokeAnnotation(new TestClass());
        }
        catch (NullPointerException e){
            System.err.println("Error: TestClass object is null.");
        }
        catch (InvocationTargetException e) {
            System.err.println("Method threw an exception: " + e.getCause());
        }
        catch (IllegalAccessException e) {
            System.err.println("Illegal access when trying to invoke the method: " + e.getMessage());
        }
    }
}