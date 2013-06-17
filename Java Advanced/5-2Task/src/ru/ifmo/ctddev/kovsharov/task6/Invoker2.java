package ru.ifmo.ctddev.kovsharov.task6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Invoker2 {
    private static boolean isSuitableMethod(Method method, String neededName, String[] arguments, List<Method> wasCalled) {
        wasCalledLoop: for (Method currentMethod : wasCalled) {
            if (!method.getName().equals(method.getName())) {
                continue;
            }
            Class<?>[] methodParams = method.getParameterTypes();
            Class<?>[] methodFromWasCalledParams = currentMethod.getParameterTypes();
            if (methodParams.length != methodFromWasCalledParams.length) {
                continue;
            }
            for (int i = 0; i < methodParams.length; i++) {
                if (!methodParams[i].equals(methodFromWasCalledParams[i])) {
                    continue wasCalledLoop;
                }
            }
            return false;
        }
        if (Modifier.isPrivate(method.getModifiers())) {
            return false;
        }
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
            List<Method> wasCalled = new ArrayList<Method>();
            while (true) {
                for (Method method : clazz.getDeclaredMethods()) {
                    if (isSuitableMethod(method, methodName, arguments, wasCalled)) {
                        wasCalled.add(method);
                        System.out.println(method);
                        method.setAccessible(true);
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
                if (clazz.equals(Object.class)) {
                    break;
                } else {
                    clazz = clazz.getSuperclass();
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
