import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class RepeatAnnotationInvoker {
    public static void invokeAnnotation(TestClass testObj) throws NullPointerException, IllegalAccessException, InvocationTargetException {
        if (testObj == null) {
            throw new NullPointerException("The testObj is null");
        }
        Method[] methods = TestClass.class.getDeclaredMethods();
        for (Method method : methods) {
            String accessModifier = Modifier.toString(method.getModifiers());
            if ((accessModifier.equals("private") || accessModifier.equals("protected")) && method.isAnnotationPresent(Repeat.class)) {
                Repeat repeatAnnotation = method.getAnnotation(Repeat.class);
                int repeatCount = repeatAnnotation.value();
                method.setAccessible(true);
                Object[] defaultParameters = fillParameters(method.getParameterTypes());
                for (int i = 0; i < repeatCount; i++) {
                    method.invoke(testObj, defaultParameters);
                }
            }
        }
    }

    private static Object[] fillParameters(Class<?>[] parameterTypes) {
        Object[] filledParams = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> param = parameterTypes[i];
            if (param == int.class) {
                filledParams[i] = 5;
            } else if (param == float.class) {
                filledParams[i] = 2.2f;
            } else if (param == double.class) {
                filledParams[i] = 2.2234234234;
            } else if (param == String.class) {
                filledParams[i] = "Default string";
            } else if (param == List.class) {
                filledParams[i] = new ArrayList<>();
            } else {
                filledParams[i] = null;
            }
        }
        return filledParams;

    }
}
