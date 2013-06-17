import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Invoker {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Not enough arguments");
            return;
        }
        String className = args[0];
        String methodName = args[1];
        String[] arguments = new String[args.length - 2];
        Class argClass[] = new Class[args.length - 2];

        for (int i = 2; i < args.length; i++) {
            arguments[i - 2] = args[i];
            argClass[i - 2] = args[i].getClass();
        }

        try {
            Class<?> clazz = Class.forName(className);
            int currentModifier = clazz.getModifiers();

            if (Modifier.isInterface(currentModifier)) {
                System.out.println(String.format("Class %s is an interface, cannot create an instance", className));
                return;
            }

            if (Modifier.isAbstract(currentModifier)) {
                System.out.println(String.format("Class %s is an abstract, cannot create an instance", className));
                return;
            }

            Object loadedClass;
            try {
                loadedClass = clazz.newInstance();
            } catch (InstantiationException e) {
                System.out.println(String.format("The class %s is a primitive type, or void; " +
                        "or if the class has no nullary constructor", className));
                e.printStackTrace();
                return;
            } catch (IllegalAccessException e) {
                System.out.println(String.format("The class %s or its nullary constructor is not accessible", className));
                e.printStackTrace();
                return;
            }
            boolean methodIsFound = false;
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.getName().equals(methodName)) {
                    Class<?>[] methodParameterTypes = method.getParameterTypes();
                    if (methodParameterTypes.length != arguments.length) {
                        continue;
                    }
                    boolean isCorrect = true;
                    for (Class<?> parameter : methodParameterTypes) {
                        isCorrect &= parameter.isAssignableFrom(String.class);
                    }

                    if (!isCorrect) {
                        continue;
                    }

                    System.out.println(method);
                    methodIsFound = true;
                    try {
                        method.invoke(loadedClass, arguments);
                    } catch (IllegalAccessException e) {
                        System.out.println(String.format("%s method is inaccessible", method));
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        System.out.println(String.format("%s method has thrown an exception.", method));
                        e.printStackTrace();
                    }

                }
            }
            if (!methodIsFound) {
                System.out.println("No such method found with correct signature");
            }
        } catch (ClassNotFoundException e) {
            System.out.println(String.format("Class %s not found", className));
        }
    }
}
