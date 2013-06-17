package ru.ifmo.ctddev.kovsharov.task6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Invoker {
    private static boolean isSuitableMethod(Method method, String neededName, String[] arguments) {
        if (!method.getName().equals(neededName)) {
            return false;
        }
        Class<?>[] methodParameterTypes = method.getParameterTypes();
        if (methodParameterTypes.length != arguments.length) {
            return false;
        }
        for (Class<?> parameter : methodParameterTypes) {
            if (!parameter.isAssignableFrom(String.class)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Not enough arguments passed");
            return;
        }
        String className = args[0];
        String methodName = args[1];
        String[] arguments = Arrays.copyOfRange(args, 2, args.length);
        try {
            Class<?> clazz = Class.forName(className);
            if (Modifier.isInterface(clazz.getModifiers())) {
                System.out.println(className + " class is an interface");
                return;
            }

            if (Modifier.isAbstract(clazz.getModifiers())) {
                System.out.println(className + " class is an interface");
                return;
            }

            Object classInstance;
            try {
                classInstance = clazz.newInstance();
            } catch (InstantiationException e) {
                System.out.println("The class is a primitive type, or void, or if the class has no nullary constructor");
                e.printStackTrace();
                return;
            } catch (IllegalAccessException e) {
                System.out.println("The class nullary constructor is not accessible");
                e.printStackTrace();
                return;
            }
            boolean methodWasFound = false;
            for (Method method : clazz.getMethods()) {
                if (isSuitableMethod(method, methodName, arguments)) {
                    System.out.println(method);
                    methodWasFound = true;
                    try {
                        method.invoke(classInstance, arguments);
                        System.out.println(classInstance);
                    } catch (IllegalAccessException e) {
                        System.out.println(method + " method is inaccessible");
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        System.out.println(method + " method has thrown an exception.");
                        e.printStackTrace();
                    }
                }
            }
            if (!methodWasFound) {
                System.out.println("No such method found");
            }
        } catch (ClassNotFoundException e) {
            System.out.println(className + " class not found");
        }
    }
}
